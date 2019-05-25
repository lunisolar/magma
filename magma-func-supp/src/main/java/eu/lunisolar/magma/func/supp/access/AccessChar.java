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

package eu.lunisolar.magma.func.supp.access;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR

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
 * Interface representing a value(s) that can be optionally combined with additional arguments and function call that might produce some other value.
 * Interface implementation is not necessarily holding nor owning the value(s).
 */
@SuppressWarnings("UnusedDeclaration")
public interface AccessChar {

	static AccessChar wrap(AccessChar lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	char accessChar();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseChar(char a) {
		// NOOP
	}

	default void useWith(LCharConsumer accessFunction) {
		char tuple = accessChar();
		accessFunction.accept(tuple);
	}

	default void useWith(char a2, LBiCharConsumer accessFunction) {
		char tuple = accessChar();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(char a1, LBiCharConsumer.LChar1Char0Cons accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptChar1Char0(tuple, a1);
	}

	default void useWith(int a2, LCharIntConsumer accessFunction) {
		char tuple = accessChar();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a2, LCharIntConsumer.LIntCharCons accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptIntChar(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumer<T1, T2> accessFunction) {
		char tuple = accessChar();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptObj0Char2Obj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptChar2Obj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjCharConsumer<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.accept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjCharConsumer.LCharObjCons<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptCharObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieCharConsumer<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptObjCharInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptIntObjChar(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptIntCharObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptCharObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.acceptCharIntObj(tuple, a2, a1);
	}

	default char useWith(char a2, LCharBinaryOperator accessFunction) {
		char tuple = accessChar();
		char retval = accessFunction.applyAsChar(tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default char useWith(LCharUnaryOperator accessFunction) {
		char tuple = accessChar();
		char retval = accessFunction.applyAsChar(tuple);
		releaseChar(tuple);
		return retval;
	}

	default byte useWith(LCharToByteFunction accessFunction) {
		char tuple = accessChar();
		byte retval = accessFunction.applyAsByte(tuple);
		releaseChar(tuple);
		return retval;
	}

	default double useWith(LCharToDblFunction accessFunction) {
		char tuple = accessChar();
		double retval = accessFunction.applyAsDbl(tuple);
		releaseChar(tuple);
		return retval;
	}

	default float useWith(LCharToFltFunction accessFunction) {
		char tuple = accessChar();
		float retval = accessFunction.applyAsFlt(tuple);
		releaseChar(tuple);
		return retval;
	}

	default int useWith(LCharToIntFunction accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.applyAsInt(tuple);
		releaseChar(tuple);
		return retval;
	}

	default long useWith(LCharToLongFunction accessFunction) {
		char tuple = accessChar();
		long retval = accessFunction.applyAsLong(tuple);
		releaseChar(tuple);
		return retval;
	}

	default short useWith(LCharToSrtFunction accessFunction) {
		char tuple = accessChar();
		short retval = accessFunction.applyAsSrt(tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LBiCharFunction<R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.apply(tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R> R useWith(char a1, LBiCharFunction.LChar1Char0Func<R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyChar1Char0(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunction<T1, T2, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyObj0Char2Obj1(a1, tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyChar2Obj0Obj1(tuple, a1, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R> R useWith(LCharFunction<R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.apply(tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjCharFunction<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.apply(a1, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjCharFunction.LCharObjFunc<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyCharObj(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntCharFunction<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyObjCharInt(a1, tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyIntObjChar(a2, a1, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyIntCharObj(a2, tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyCharObjInt(tuple, a1, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.applyCharIntObj(tuple, a2, a1);
		releaseChar(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieCharFunction<T> accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.applyAsInt(a1, a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.applyAsIntObjCharInt(a1, tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.applyAsIntIntObjChar(a2, a1, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.applyAsIntIntCharObj(a2, tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.applyAsIntCharObjInt(tuple, a1, a2);
		releaseChar(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.applyAsIntCharIntObj(tuple, a2, a1);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(char a2, LBiCharPredicate accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.test(tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(char a1, LBiCharPredicate.LChar1Char0Pred accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testChar1Char0(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicate<T1, T2> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testObj0Char2Obj1(a1, tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testChar2Obj0Obj1(tuple, a1, a2);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(int a2, LCharIntPredicate accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.test(tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(int a2, LCharIntPredicate.LIntCharPred accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testIntChar(a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(LCharPredicate accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.test(tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjCharPredicate<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.test(a1, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjCharPredicate.LCharObjPred<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testCharObj(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntCharPredicate<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testObjCharInt(a1, tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testIntObjChar(a2, a1, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testIntCharObj(a2, tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testCharObjInt(tuple, a1, a2);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.testCharIntObj(tuple, a2, a1);
		releaseChar(tuple);
		return retval;
	}

}
