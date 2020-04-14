/*
 *  This file is part of "lunisolar-lava".
 *  (C) Copyright 2018 Jakub Wach
 */

package eu.lunisolar.magma.test.random;

import javax.annotation.Nullable;
import java.util.concurrent.*;
import java.util.logging.*;
import java.util.stream.*;

import static eu.lunisolar.magma.func.consumer.primitives.LIntConsumer.fromTill;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.*;

@SuppressWarnings("unchecked")
public class Series<T> {

    private final static Logger LOGGER = Logger.getLogger(Series.class.getName());

    private Object[] poolA;
    private Object[] poolB;
    private Object[] series;

    private SeriesParams<T> params;

    private Series(SeriesParams<T> params, boolean useAltPathEnforcer, @Nullable T altPathEnforcer) {

        this.params = params.validate();

        poolA = new Object[params.poolASize()];
        poolB = new Object[params.poolBSize()];
        series = new Object[params.size()];

        fromTill(0, poolA.length, i -> poolA[i] = params.poolAProducer().get());
        fromTill(0, poolB.length, i -> poolB[i] = params.poolBProducer().get());

//        fromTill(0, series.length, i -> series[i] = createSampleValue());

        if (useAltPathEnforcer) {
            rangeClosed(0, series.length - 1).parallel()
                                             .mapToObj(i -> i == 0 ? altPathEnforcer : createSampleValue()).collect(toList()).toArray(series);
        } else {
            rangeClosed(0, series.length - 1).parallel().mapToObj(i -> createSampleValue()).collect(toList()).toArray(series);
        }

        LOGGER.log(Level.INFO, String.format("Series '%s' prepared: %s", params.name(), params));
    }

    public static <T> Series<T> series(SeriesParams<T> params) {
        return new Series<>(params, false, null);
    }

    public static <T> Series<T> series(SeriesParams<T> params, @Nullable T altPathEnforcer) {
        return new Series<>(params, true, altPathEnforcer);
    }

    private Object createSampleValue() {

        var rnd = ThreadLocalRandom.current();
        var v = rnd.nextInt(101);

        if (params.percentageA() > 0 && v <= params.percentageA()) {
            return poolA[rnd.nextInt(poolA.length)];
        }

        if (params.percentageB() > 0 && params.percentageA() < v && v <= (params.percentageA() + params.percentageB())) {
            return poolB[rnd.nextInt(poolB.length)];
        }

        return params.poolCProducer().get();
    }

    public T v(int index) {
        return (T) series[index];
    }

    public int size() {
        return series.length;
    }

    public Stream<T> stream() {
        return (Stream) Stream.of(series);
    }
}
