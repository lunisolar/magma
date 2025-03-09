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

import eu.lunisolar.magma.func.tuple.LDblSingle;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class AtomicDoubleSingleTest {

    @Test
    void setValueIf_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        a.setValueIfCurrent(2, v -> v == 0);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void setValueIf_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        a.setValueIfCurrent(2, v -> v == 2);
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void setValueIf2_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        a.setValueIf(2, (v, x) -> v == 0);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void setValueIf2_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        a.setValueIf(2, (v, x) -> v == 2);
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void set_get() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        a.set(2);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void lazySet() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        a.lazySet(2);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void getAndSet() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.getAndSet(2)).mustBeEqual(0);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void compareAndSet_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndSet(0, 2)).mustBeTrue();
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void compareAndSet_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndSet(1, 2)).mustBeFalse();
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void weakCompareAndSetPlain_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetPlain(0, 2)).mustBeTrue();
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void weakCompareAndSetPlain_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetPlain(1, 2)).mustBeFalse();
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void getAndIncrement() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.getAndIncrement()).mustBeEqual(0);
        attest(a.get()).mustBeEqual(1);
    }

    @Test
    void getAndDecrement() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.getAndDecrement()).mustBeEqual(0);
        attest(a.get()).mustBeEqual(-1);
    }

    @Test
    void incrementAndGet() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.incrementAndGet()).mustBeEqual(1);
        attest(a.get()).mustBeEqual(1);
    }

    @Test
    void decrementAndGet() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.decrementAndGet()).mustBeEqual(-1);
        attest(a.get()).mustBeEqual(-1);
    }

    @Test
    void getAndUpdate() {
        var a = LDblSingle.atomicOf(2);
        attest(a.get()).mustBeEqual(2);
        attest(a.getAndUpdate(v -> v + 2)).mustBeEqual(2);
        attest(a.get()).mustBeEqual(4);
    }

    @Test
    void updateAndGet() {
        var a = LDblSingle.atomicOf(2);
        attest(a.get()).mustBeEqual(2);
        attest(a.updateAndGet(v -> v + 2)).mustBeEqual(4);
        attest(a.get()).mustBeEqual(4);
    }

    @Test
    void getAndAccumulate() {
        var a = LDblSingle.atomicOf(2);
        attest(a.get()).mustBeEqual(2);
        attest(a.getAndAccumulate(3, (x1, x2) -> x1 + x2 + 2)).mustBeEqual(2);
        attest(a.get()).mustBeEqual(7);
    }

    @Test
    void accumulateAndGet() {
        var a = LDblSingle.atomicOf(2);
        attest(a.get()).mustBeEqual(2);
        attest(a.accumulateAndGet(3, (x1, x2) -> x1 + x2 + 2)).mustBeEqual(7);
        attest(a.get()).mustBeEqual(7);
    }

    @Test
    void setPlain_getPlain() {
        var a = LDblSingle.atomicOf(0);
        attest(a.getPlain()).mustBeEqual(0);
        a.setPlain(2);
        attest(a.getPlain()).mustBeEqual(2);
    }

    @Test
    void setOpaque_getOpaque() {
        var a = LDblSingle.atomicOf(0);
        attest(a.getOpaque()).mustBeEqual(0);
        a.setOpaque(2);
        attest(a.getOpaque()).mustBeEqual(2);
    }

    @Test
    void setRelease_getAcquire() {
        var a = LDblSingle.atomicOf(0);
        attest(a.getAcquire()).mustBeEqual(0);
        a.setRelease(2);
        attest(a.getAcquire()).mustBeEqual(2);
    }

    @Test
    void compareAndExchange_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndExchange(0, 2)).mustBeEqual(0);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void compareAndExchange_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndExchange(1, 2)).mustBeEqual(0);
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void compareAndExchangeAcquire_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndExchangeAcquire(0, 2)).mustBeEqual(0);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void compareAndExchangeAcquire_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndExchangeAcquire(1, 2)).mustBeEqual(0);
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void compareAndExchangeRelease_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndExchangeRelease(0, 2)).mustBeEqual(0);
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void compareAndExchangeRelease_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.compareAndExchangeRelease(1, 2)).mustBeEqual(0);
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void weakCompareAndSetVolatile_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetVolatile(0, 2)).mustBeTrue();
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void weakCompareAndSetVolatile_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetVolatile(1, 2)).mustBeFalse();
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void weakCompareAndSetAcquire_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetAcquire(0, 2)).mustBeTrue();
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void weakCompareAndSetAcquire_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetAcquire(1, 2)).mustBeFalse();
        attest(a.get()).mustBeEqual(0);
    }

    @Test
    void weakCompareAndSetRelease_match() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetRelease(0, 2)).mustBeTrue();
        attest(a.get()).mustBeEqual(2);
    }

    @Test
    void weakCompareAndSetRelease_noMatch() {
        var a = LDblSingle.atomicOf(0);
        attest(a.get()).mustBeEqual(0);
        attest(a.weakCompareAndSetRelease(1, 2)).mustBeFalse();
        attest(a.get()).mustBeEqual(0);
    }
}
