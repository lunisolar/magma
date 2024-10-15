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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.basics.exceptions.X;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.opt.Opt;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.asserts.TestFlow.test;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

@SuppressWarnings("unchecked")
public class OptTest {

    @Test void notNull() {
        attestThrownBy(() -> Opt.notNull(null))
                .mustBeExactlyInstanceOf(NullPointerException.class)
                .mustEx(Have::msgEqualEx, "Argument 'value' must not be null.");

        attestThrownBy(() -> Opt.notNull(null, X::noSuchElement, "Message1"))
                .mustBeExactlyInstanceOf(NoSuchElementException.class)
                .mustEx(Have::msgEqualEx, "Message1");

        attestThrownBy(() -> Opt.notNull(null, X::noSuchElement, "Message1 %s", "p1"))
                .mustBeExactlyInstanceOf(NoSuchElementException.class)
                .mustEx(Have::msgEqualEx, "Message1 p1");

        attestThrownBy(() -> Opt.notNull(null, X::noSuchElement, "Message1 %s %s", "p1", "p2"))
                .mustBeExactlyInstanceOf(NoSuchElementException.class)
                .mustEx(Have::msgEqualEx, "Message1 p1 p2");

        attestThrownBy(() -> Opt.notNull(null, X::noSuchElement, "Message1 %s - %s - %s", "p1", "p2", "p3"))
                .mustBeExactlyInstanceOf(NoSuchElementException.class)
                .mustEx(Have::msgEqualEx, "Message1 p1 - p2 - p3");
    }

    @Test
    public void filterAndMap() {
        var sut = Opt.obj(Integer.valueOf(1));

        attest(sut.filterAndMap(Integer.class).nullable()).mustEx(Be::equalEx, 1);
        attest(sut.filterAndMap(String.class).nullable()).mustEx(Be::NullEx);
    }


    @Test
    public void ifPresent() {
        var sut = Opt.obj(1);

        AtomicReference result = new AtomicReference(null);
        sut.ifPresent(v -> {
            result.set("done");
        });

        attest(result).mustEx(P.haveEx(AtomicReference::get, P::equalEx, "done"));
    }

    @Test
    public void doIf_null() {
        var sut = Opt.obj(1);

        AtomicReference result = new AtomicReference(null);
        sut.doIf(P::Null, v -> {
            result.set("done");
        });

        attest(result).mustEx(P.haveEx(AtomicReference::get, P::notEqualEx, "done"));
    }

    @Test
    public void doIfNot_null() {
        var sut = Opt.obj(1);

        AtomicReference result = new AtomicReference(null);
        sut.doIfNot(P::Null, v -> {
            result.set("done");
        });

        attest(result).mustEx(P.haveEx(AtomicReference::get, P::equalEx, "done"));
    }

    @Test
    public void iterator_empty() {

        Opt<Object> opt = Opt.obj(null);
        attest(opt.iterator())
                .check(Iterator::hasNext, hasNext -> hasNext.mustBeEqual(false));

        attestThrownBy(() -> opt.iterator().next())
                .mustBeExactlyInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void iterator_not_empty() {
        test().given(()-> new Object() {
            Integer value = Integer.valueOf(45);
            Opt<Integer> opt = Opt.obj(value);
            Iterator<Integer> iterator = opt.iterator();
            Integer next;
        }).precondition(stage -> {
            attest(stage.iterator).check(Iterator::hasNext, hasNext -> hasNext.mustBeEqual(true));
        }).when(stage -> {
            stage.next = stage.iterator.next();
        }).when(stage -> {
            attest(stage.next).mustBeSame(stage.value);
        }).aftermath(stage -> {
            attest(stage.iterator).check(Iterator::hasNext, hasNext -> hasNext.mustBeEqual(false));
            attestThrownBy(() -> stage.iterator.next())
                    .mustBeExactlyInstanceOf(NoSuchElementException.class);
        });
    }

}
