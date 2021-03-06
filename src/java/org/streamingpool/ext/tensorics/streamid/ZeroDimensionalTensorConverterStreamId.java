// @formatter:off
/**
*
* This file is part of streaming pool (http://www.streamingpool.org).
*
* Copyright (c) 2017-present, CERN. All rights reserved.
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
*
*/
// @formatter:on

package org.streamingpool.ext.tensorics.streamid;

import static java.util.function.Function.identity;
import static org.tensorics.core.lang.Tensorics.scalarOf;

import java.util.function.Function;

import org.streamingpool.core.service.StreamId;
import org.streamingpool.core.service.streamid.DerivedStreamId;
import org.tensorics.core.tensor.Position;
import org.tensorics.core.tensor.Tensor;

/**
 * {@link DerivedStreamId} that encapsulate a stream of values (T) into a stream of zero-dimensional {@link Tensor}s. It
 * provides to possibility to map the data type before creating the {@link Tensor}.
 *
 * @author acalia
 * @param <T> the type of the source data stream
 * @param <U> the type of the Tensor values
 */
public class ZeroDimensionalTensorConverterStreamId<T, U> extends DerivedStreamId<T, Tensor<U>> {
    private static final long serialVersionUID = 1L;

    public static <T> ZeroDimensionalTensorConverterStreamId<T, T> of(StreamId<T> sourceStreamId) {
        return new ZeroDimensionalTensorConverterStreamId<>(sourceStreamId, identity(), any -> Position.empty());
    }

    public static <T, U> ZeroDimensionalTensorConverterStreamId<T, U> withValueMapper(StreamId<T> sourceStreamId,
            Function<T, U> valueMapper) {
        return new ZeroDimensionalTensorConverterStreamId<>(sourceStreamId, valueMapper, any -> Position.empty());
    }

    public static <T> ZeroDimensionalTensorConverterStreamId<T, T> withContextMapper(StreamId<T> sourceStreamId,
            Function<T, Position> contextMapper) {
        return new ZeroDimensionalTensorConverterStreamId<>(sourceStreamId, identity(), contextMapper);
    }

    public static <T, U> ZeroDimensionalTensorConverterStreamId<T, U> of(StreamId<T> sourceStreamId,
            Function<T, U> valueMapper, Function<T, Position> contextMapper) {
        return new ZeroDimensionalTensorConverterStreamId<>(sourceStreamId, valueMapper, contextMapper);
    }

    private ZeroDimensionalTensorConverterStreamId(StreamId<T> sourceStreamId, Function<T, U> valueMapper,
            Function<T, Position> contextMapper) {
        super(sourceStreamId, value -> scalarOf(valueMapper.apply(value)).withContext(contextMapper.apply(value)));
    }

}
