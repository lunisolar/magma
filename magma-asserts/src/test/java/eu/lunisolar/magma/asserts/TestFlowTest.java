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

package eu.lunisolar.magma.asserts;

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.check.Checks;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.asserts.TestFlow.test;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

public class TestFlowTest {

    @Test public void positiveCase1() {

        test().given(() -> {
            return 1;
        }).when(sut -> {

        }).then(sut -> {
            Checks.attest(sut.toString()).mustEx(Be::equalEx, "1");
        });
    }

    @Test public void positiveCase2() {

        test().given(() -> {
            return 1;
        }).then(sut -> {
            Checks.attest(sut.toString()).mustEx(Be::equalEx, "1");
        });
    }

    @Test public void positiveCase3() {

        test().given(() -> {
            return 1;
        }).then(sut -> {
            Checks.attest(sut.toString()).mustEx(Be::equalEx, "1");
        }).continuing(
        ).when(sut -> {

        });
    }

    @Test public void positiveCase4() {

        StringBuilder sb = new StringBuilder();

        test(m -> sb.append(m).append("\n")).given(() -> {
            return 1;
        }).precondition("initial state is", sut -> {

        }).step("Step1", t -> {
            t.when("when1", sut -> {
                //...
            }).then("then1", sut -> {
                Checks.attest(sut.toString()).mustEx(Be::equalEx, "1");
            });
        }).step("Step2", t -> {
            t.when("when2", sut -> {
                //...
            }).then(sut -> {
                Checks.attest(sut.toString()).mustEx(Be::equalEx, "1");
            });
        }).aftermath("in the end", sut -> {

        });

        Checks.attest(sb.toString()).mustEx(Be::equalEx, "Given: ...\n" +
                                                    "Precondition: initial state is\n" +
                                                    "STEP: Step1\n" +
                                                    "When: when1\n" +
                                                    "Then: then1\n" +
                                                    "STEP: Step2\n" +
                                                    "When: when2\n" +
                                                    "Then: ...\n" +
                                                    "Aftermath: in the end\n");
    }

    @Test public void negativeCase_AssertionError() {

        final var message = "The message is important.";
        final var theException = new AssertionError(message);

        attestThrownBy(() ->
                                   test().given(() -> {
                                       return 1;
                                   }).then(sut -> {
                                       throw theException;
                                   })
        )
                .mustEx(Have::msgEqualEx, message)
                .mustEx(Be::sameEx,theException);
    }

    @Test public void negativeCase_RuntimeException() {

        final var message = "The message is important.";
        final var theException = new RuntimeException(message);

        attestThrownBy(() ->
                                   test().given(() -> {
                                       return 1;
                                   }).then(sut -> {
                                       throw theException;
                                   })
        )
                .mustEx(Have::msgEqualEx, message)
                .mustEx(Be::sameEx,theException);
    }

    @Test public void negativeCase_Exception() {

        final var message = "The message is important.";
        final var theException = new Exception(message);

        attestThrownBy(() ->
                                   test().given(() -> {
                                       return 1;
                                   }).then(sut -> {
                                       throw theException;
                                   })
        )
                .mustEx(Have::msgEqualEx, message)
                .mustEx(Be::sameEx,theException);
    }

    @Test public void howToUseTestFlowState() {

        attestThrownBy(() ->
            test().given(() -> new TestFlow.State() {
                    String someState = new String("");
                })
                .then(state -> {
                    attest(state.someState).mustEx(Be::equalEx, "otherValue");
                })
        ).mustEx(Have::msgEqualEx, "Check/attest [?==''](param: 'otherValue'): <> must be equal to <otherValue>.");
    }

    @Test public void stateWithOwnStage() {
        var mainStage = new TestFlow.State() {
            String someState = new String("11");
        };
        var stepStage = new TestFlow.State() {
            String someState = new String("22");
        };

        test().given(()-> mainStage)
                    .step(()-> stepStage, flow -> flow
                        .when((main, step) -> {
                            main.someState = "otherValue1";
                            step.someState = "otherValue2";
                        }).then((main, step) -> {
                            attest(main.someState).mustEx(Be::equalEx, "otherValue1");
                            attest(step.someState).mustEx(Be::equalEx, "otherValue2");
                        })
                );

        attest(mainStage.someState).mustEx(Be::equalEx, "otherValue1");
        attest(stepStage.someState).mustEx(Be::equalEx, "otherValue2");
    }

}