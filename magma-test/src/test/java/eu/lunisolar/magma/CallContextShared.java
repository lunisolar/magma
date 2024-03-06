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

package eu.lunisolar.magma;

import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.function.LBiFunction;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.supplier.LSupplier;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

class CallContextShared {

    protected static final ThreadLocal<List<String>> L = ThreadLocal.withInitial(ArrayList::new);
    protected static List<String> l() {return L.get();}

    protected static Object[] p(
            LSupplier<?> init1, LBiConsumer<?, Throwable> finish1,
            LSupplier<?> init2, LBiConsumer<?, Throwable> finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        return new Object[]{init1, finish1, init2, finish2, function, exChecker, expectedLog};
    }

    protected static final class MyError extends Error {
        public MyError(String message) {super(message);}
    }

    protected static final LBiFunction<String, String, String> FUNC     = (a1, a2) -> {
        l().add("" + a1);
        l().add("" + a2);
        return a1 + a2;
    };
    protected static final String                              ARG1     = "a1";
    protected static final String                              ARG2     = "a2";
    protected static final String                              ExEX_F   = "E1";
    protected static final LBiFunction<String, String, String> EX_FUNC  = (a1, a2) -> {throw new Exception(ExEX_F);};
    protected static final LBiFunction<String, String, String> ERR_FUNC = (a1, a2) -> {throw new MyError(ExEX_F);};
    protected static final String                              i1Log    = "C1.init";
    protected static final String                              i2Log    = "C2.init";
    protected static final String                              f1Log    = "C1.finalize";
    protected static final String                              f2Log    = "C2.finalize";
    protected static final LSupplier<Object>                   i1       = () -> {
        l().add(i1Log);
        return i1Log;
    };
    protected static final LSupplier<Object>                   i2       = () -> {
        l().add(i2Log);
        return i2Log;
    };
    protected static final LBiConsumer<Object, Throwable>      f1       = (state, e) -> {
        l().add(f1Log);
        attest(state).mustBeSame(i1Log);
    };
    protected static final LBiConsumer<Object, Throwable>      f2       = (state1, e1) -> {
        l().add(f2Log);
        attest(state1).mustBeSame(i2Log);
    };
    protected static final LSupplier<?>                        EX_I1    = () -> {throw new Exception("I1");};
    protected static final LBiConsumer<?, Throwable>           EX_F1    = (state, e) -> {throw new Exception("F1");};
    protected static final LSupplier<?>                        EX_I2    = () -> {throw new Exception("I2");};
    protected static final LBiConsumer<?, Throwable>           EX_F2    = (state, e) -> {throw new Exception("F2");};
    protected static final LSupplier<?>                        ERR_I1   = () -> {throw new MyError("I1");};
    protected static final LBiConsumer<?, Throwable>           ERR_F1   = (state, e) -> {throw new MyError("F1");};
    protected static final LSupplier<?>                        ERR_I2   = () -> {throw new MyError("I2");};
    protected static final LBiConsumer<?, Throwable>           ERR_F2   = (state, e) -> {throw new MyError("F2");};

    protected static Object[][] exceptionHandlingCases() {
        return new Object[][]{
                p(i1, f1, i2, f2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, ExEX_F)
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuppressedEx),
                  i1Log, i2Log, f2Log, f1Log
                ),

                //<editor-fold desc="single exception in context">
                p(EX_I1, f1, i2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "I1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuppressedEx)
                ),
                p(i1, EX_F1, i2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "F1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuppressedEx),
                  i1Log, i2Log, ARG1, ARG2, f2Log
                ),
                p(i1, f1, EX_I2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "I2")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuppressedEx),
                  i1Log, f1Log
                ),
                p(i1, f1, i2, EX_F2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "F2")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuppressedEx),
                  i1Log, i2Log, ARG1, ARG2, f1Log
                ),
                //</editor-fold>

                //<editor-fold desc="both main function and finisher fails">
                p(i1, EX_F1, i2, f2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::suppressedEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log, i2Log, f2Log
                ),
                p(i1, f1, i2, EX_F2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F2")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log, i2Log, f1Log
                ),
                //</editor-fold>

                //<editor-fold desc="main function and both finisher fails">
                p(i1, EX_F1, i2, EX_F2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F2")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  })
                                  .check(e -> e.getSuppressed()[1], ch2 -> {
                                      ch2.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                         .mustEx(Have::msgEqualEx, "F1")
                                         .mustEx(Have::noCauseEx)
                                         .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log, i2Log
                ),
                //</editor-fold>

                //<editor-fold desc="last initializer + first finisher fail">
                p(i1, EX_F1, EX_I2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "I2")
                                  .mustEx(Have::noCauseEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log
                ),
                //</editor-fold>

                //<editor-fold desc="Error always prevails">

                p(i1, ERR_F1, EX_I2, f2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                  .mustEx(Have::msgEqualEx, "F1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::suppressedEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "I2")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log
                ),

                p(i1, ERR_F1, ERR_I2, f2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                  .mustEx(Have::msgEqualEx, "I2")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::suppressedEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log
                ),

                p(i1, EX_F1, ERR_I2, f2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                  .mustEx(Have::msgEqualEx, "I2")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::suppressedEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log
                ),

                p(i1, EX_F1, i2, f2, ERR_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::suppressedEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log, i2Log, f2Log
                ),

                p(i1, ERR_F1, i2, f2, ERR_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::suppressedEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuppressedEx);
                                  }),
                  i1Log, i2Log, f2Log
                ),

                p(ERR_I1, f1, i2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                  .mustEx(Have::msgEqualEx, "I1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuppressedEx)
                ),

                p(i1, f1, i2, ERR_F2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, MyError.class)
                                  .mustEx(Have::msgEqualEx, "F2")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuppressedEx),
                  i1Log, i2Log, ARG1, ARG2, f1Log
                ),

                //</editor-fold>
        };
    }

    public static Object[][] simpleTest_statesData() {
        return new Object[][]{
                {"3", "4"},
                {"2", "3"},
                {"2", null},
                {null, "3"},
                {null, null}
        };
    }

}
