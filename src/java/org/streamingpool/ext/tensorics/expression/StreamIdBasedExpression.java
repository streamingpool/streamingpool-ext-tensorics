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

package org.streamingpool.ext.tensorics.expression;

import static java.util.Objects.requireNonNull;

import org.streamingpool.core.service.StreamId;
import org.tensorics.core.tree.domain.ResolvedExpression;

/**
 * An expression which can be used within the tensorics DSL. It represents values which can be looked up from a stream
 * in the streaming pool. It is always a leaf of the expression tree.
 *
 * @author kfuchsbe, caguiler
 * @param <R> the return type of the expression (and thus the type of the values that the discovered stream will have to
 *            produce)
 */
public class StreamIdBasedExpression<R> extends UnresolvedStreamIdBasedExpression<R> {
    private static final long serialVersionUID = 1L;

    private final StreamId<R> streamId;

    protected StreamIdBasedExpression(StreamId<R> streamId) {
        super(ResolvedExpression.of(streamId));
        this.streamId = requireNonNull(streamId, "streamId must not be null.");
    }

    public static <R> StreamIdBasedExpression<R> of(StreamId<R> streamId) {
        return new StreamIdBasedExpression<>(streamId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((streamId() == null) ? 0 : streamId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StreamIdBasedExpression<?> other = (StreamIdBasedExpression<?>) obj;
        if (streamId() == null) {
            if (other.streamId() != null) {
                return false;
            }
        } else if (!streamId().equals(other.streamId())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StreamIdBasedExpression [streamId=" + streamId() + "]";
    }

    public StreamId<R> streamId() {
        return streamId;
    }

}
