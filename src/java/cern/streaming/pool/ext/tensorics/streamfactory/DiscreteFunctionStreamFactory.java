/**
 * Copyright (c) 2016 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.streaming.pool.ext.tensorics.streamfactory;

import static java.util.Optional.of;

import java.util.List;
import java.util.Optional;

import org.reactivestreams.Publisher;
import org.tensorics.core.commons.operations.Conversion;
import org.tensorics.core.function.DiscreteFunction;
import org.tensorics.core.function.MapBackedDiscreteFunction;

import cern.streaming.pool.core.service.DiscoveryService;
import cern.streaming.pool.core.service.StreamFactory;
import cern.streaming.pool.core.service.StreamId;
import cern.streaming.pool.ext.tensorics.streamid.BufferedStreamId;
import cern.streaming.pool.ext.tensorics.streamid.FunctionStreamId;
import io.reactivex.Flowable;

/**
 * Creates streams of {@link DiscreteFunction}s by means of a {@link FunctionStreamId}
 * 
 * @author caguiler, kfuchsbe
 */
@Deprecated
public class DiscreteFunctionStreamFactory implements StreamFactory {

    /* Safe, manually checked */
    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<Publisher<T>> create(StreamId<T> id, DiscoveryService discoveryService) {
        if (!(id instanceof FunctionStreamId)) {
            return Optional.empty();
        }

        FunctionStreamId<?, ?, ?> functionId = (FunctionStreamId<?, ?, ?>) id;

        return of((Publisher<T>) createFunctionStream(functionId, discoveryService));
    }

    <R, X, Y> Publisher<DiscreteFunction<X, Y>> createFunctionStream(FunctionStreamId<R, X, Y> id,
            DiscoveryService discoveryService) {

        BufferedStreamId<R> sourceStream = id.getSourceStream();
        Conversion<? super R, ? extends X> toX = id.getToX();
        Conversion<? super R, ? extends Y> toY = id.getToY();

        Flowable<List<R>> buffered = Flowable.fromPublisher(discoveryService.discover(sourceStream));

        return buffered.map(iterable -> {
            MapBackedDiscreteFunction.Builder<X, Y> functionBuilder = MapBackedDiscreteFunction.builder();

            for (R t : iterable) {
                functionBuilder.put(toX.apply(t), toY.apply(t));
            }

            return functionBuilder.build();
        });
    }

}
