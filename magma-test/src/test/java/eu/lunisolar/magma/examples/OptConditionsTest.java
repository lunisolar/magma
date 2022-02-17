/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.opt.Opt;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
public class OptConditionsTest {

    @Test
    public void filterAndMap() {
        var sut = Opt.obj(Integer.valueOf(1));

        assertThat(sut.filterAndMap(Integer.class).nullable()).isEqualTo(1);
        assertThat(sut.filterAndMap(String.class).nullable()).isNull();
    }


    @Test
    public void ifPresent() {
        var sut = Opt.obj(1);

        AtomicReference result = new AtomicReference(null);
        sut.ifPresent(v -> {
            result.set("done");
        });

        assertThat(result).hasValue("done");
    }

    @Test
    public void doIf_null() {
        var sut = Opt.obj(1);

        AtomicReference result = new AtomicReference(null);
        sut.doIf(P::Null, v -> {
            result.set("done");
        });

        assertThat(result).doesNotHaveValue("done");
    }

    @Test
    public void doIfNot_null() {
        var sut = Opt.obj(1);

        AtomicReference result = new AtomicReference(null);
        sut.doIfNot(P::Null, v -> {
            result.set("done");
        });

        assertThat(result).hasValue("done");
    }

}
