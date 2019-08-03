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

package eu.lunisolar.magma.func.predicate;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*;

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
 * Non-throwing functional interface (lambda) LBiDblPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): double a1,double a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiDblPredicate extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain2<aDouble, aDouble> { // NOSONAR

	String DESCRIPTION = "LBiDblPredicate: boolean test(double a1,double a2)";

	// boolean test(double a1,double a2) ;
	default boolean test(double a1, double a2) {
		// return nestingTest(a1,a2);
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(double a1,double a2)
	 */
	boolean testX(double a1, double a2) throws Throwable;

	default boolean tupleTest(LDblPair args) {
		return test(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(double a1, double a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiDblPredicate handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingTest(a1, a2, handling);
	}

	default boolean test(double a1, double a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBiDblPredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> test(a1, a2, exF, newMessage, messageParams);
	}

	default boolean test(double a1, double a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBiDblPredicate trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> test(a1, a2, exF);
	}

	default boolean testThen(double a1, double a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LBiDblPredicate tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2) -> testThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(double a1, double a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(double a1, double a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingTest(double a1, double a2, LBiDblPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, handling);
	}

	static boolean tryTest(double a1, double a2, LBiDblPredicate func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2);
	}

	static boolean tryTest(double a1, double a2, LBiDblPredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF, newMessage, messageParams);
	}

	static boolean tryTest(double a1, double a2, LBiDblPredicate func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF);
	}

	static boolean tryTestThen(double a1, double a2, LBiDblPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, handler);
	}

	default boolean failSafeTest(double a1, double a2, @Nonnull LBiDblPredicate failSafe) {
		try {
			return test(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2);
		}
	}

	static boolean failSafeTest(double a1, double a2, LBiDblPredicate func, @Nonnull LBiDblPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2);
		} else {
			return func.failSafeTest(a1, a2, failSafe);
		}
	}

	static LBiDblPredicate failSafe(LBiDblPredicate func, @Nonnull LBiDblPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeTest(a1, a2, func, failSafe);
	}

	default boolean doIf(double a1, double a2, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(double a1, double a2, @Nonnull LBiDblPredicate predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, action);
	}

	static boolean doIf(double a1, double a2, @Nonnull LBiDblPredicate predicate, @Nonnull LBiDblConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, consumer);
	}

	default boolean doIf(double a1, double a2, @Nonnull LBiDblConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(double a1, double a2, LBiDblPredicate pred, ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(double a1, double a2, LBiDblPredicate pred, ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(double a1, double a2) {
		return test(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(double a1, double a2) {
		return test(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiDblPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a1, double a2, @Nonnull LBiDblPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a1, double a2, @Nonnull LBiDblPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a1, double a2, @Nonnull LBiDblPredicate func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	public default LDblPredicate lShrink(@Nonnull LDblUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> test(left.applyAsDbl(a2), a2);
	}

	public default LDblPredicate lShrink_(double a1) {
		return a2 -> test(a1, a2);
	}

	public static LDblPredicate lShrunken(@Nonnull LDblUnaryOperator left, @Nonnull LBiDblPredicate func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LDblPredicate lShrunken_(double a1, @Nonnull LBiDblPredicate func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LDblPredicate rShrink(@Nonnull LDblUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> test(a1, right.applyAsDbl(a1));
	}

	public default LDblPredicate rShrink_(double a2) {
		return a1 -> test(a1, a2);
	}

	public static LDblPredicate rShrunken(@Nonnull LDblUnaryOperator right, @Nonnull LBiDblPredicate func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LDblPredicate rShrunken_(double a2, @Nonnull LBiDblPredicate func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LBiDblPredicate uncurry(@Nonnull LDblFunction<LDblPredicate> func) {
		Null.nonNullArg(func, "func");
		return (double a1, double a2) -> func.apply(a1).test(a2);
	}

	/** Change function to consumer that ignores output. */
	public default LBiDblConsumer toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LBiDblPredicate beforeDo(@Nonnull LBiDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a1, double a2) -> {
			before.accept(a1, a2);
			return test(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LBiDblPredicate afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a1, double a2) -> {
			final boolean retval = test(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(double a1, double a2) {
		return () -> this.test(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LBiDblPredicate constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LBiDblPredicate test1st(@Nonnull LDblPredicate func) {
		return (a1, a2) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LBiDblPredicate test2nd(@Nonnull LDblPredicate func) {
		return (a1, a2) -> func.test(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBiDblPredicate biDblPred(final @Nonnull LBiDblPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBiDblPredicate recursive(final @Nonnull LFunction<LBiDblPredicate, LBiDblPredicate> selfLambda) {
		final LBiDblPredicateSingle single = new LBiDblPredicateSingle();
		LBiDblPredicate func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBiDblPredicateSingle implements LSingle<LBiDblPredicate>, LBiDblPredicate {
		private LBiDblPredicate target = null;

		@Override
		public boolean testX(double a1, double a2) throws Throwable {
			return target.testX(a1, a2);
		}

		@Override
		public LBiDblPredicate value() {
			return target;
		}
	}

	@Nonnull
	static LBiDblPredicate biDblPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBiDblPredicate biDblPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDbl1Dbl0Pred dbl1Dbl0Pred(final @Nonnull LDbl1Dbl0Pred lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static boolean call(double a1, double a2, final @Nonnull LBiDblPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LBiDblPredicate safe() {
		return LBiDblPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiDblPredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBiDblPredicate safe(final @Nullable LBiDblPredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBiDblPredicate> safeSupplier(final @Nullable LSupplier<LBiDblPredicate> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LBiDblPredicate negate() {
		return (a1, a2) -> !test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiDblPredicate and(@Nonnull LBiDblPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) && other.test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiDblPredicate or(@Nonnull LBiDblPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) || other.test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiDblPredicate xor(@Nonnull LBiDblPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) ^ other.test(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LBiDblPredicate isEqual(double v1, double v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBiDblPredicate compose(@Nonnull final LDblUnaryOperator before1, @Nonnull final LDblUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.applyAsDbl(v1), before2.applyAsDbl(v2));
	}

	public static LBiDblPredicate composed(@Nonnull final LDblUnaryOperator before1, @Nonnull final LDblUnaryOperator before2, LBiDblPredicate after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> biDblPredCompose(@Nonnull final LToDblFunction<? super V1> before1, @Nonnull final LToDblFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.applyAsDbl(v1), before2.applyAsDbl(v2));
	}

	public static <V1, V2> LBiPredicate<V1, V2> composed(@Nonnull final LToDblFunction<? super V1> before1, @Nonnull final LToDblFunction<? super V2> before2, LBiDblPredicate after) {
		return after.biDblPredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiDblFunction<V> boolToBiDblFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblBinaryOperator boolToDblBinaryOp(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiDblPredicate boolToBiDblPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.test(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiDblPredicate for method references. */
	@FunctionalInterface
	interface LDbl1Dbl0Pred extends LBiDblPredicate {

		boolean testDbl1Dbl0(double a2, double a1);

		@Override
		default boolean testX(double a1, double a2) {
			return this.testDbl1Dbl0(a2, a1);
		}
	}

	// </editor-fold>

	// >>> LBiDblPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(double a1, double a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double a1, double a2) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void filterForEach(IndexedRead<C1, aDouble> ia1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LBiDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiToDblFunction<Object> oiFunc1 = (LOiToDblFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			double a1 = oiFunc1.applyAsDbl(source1, i);
			double a2 = oiFunc2.applyAsDbl(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void filterIterate(SequentialRead<C1, I1, aDouble> sa1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LBiDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToDblFunction<Object> nextFunc1 = (LToDblFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			double a1 = nextFunc1.applyAsDbl(iterator1);
			double a2 = oiFunc2.applyAsDbl(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void filterIterate(IndexedRead<C1, aDouble> ia1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LBiDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiToDblFunction<Object> oiFunc1 = (LOiToDblFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			double a1 = oiFunc1.applyAsDbl(source1, i);
			double a2 = nextFunc2.applyAsDbl(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void filterIterate(SequentialRead<C1, I1, aDouble> sa1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LBiDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToDblFunction<Object> nextFunc1 = (LToDblFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			double a1 = nextFunc1.applyAsDbl(iterator1);
			double a2 = nextFunc2.applyAsDbl(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
