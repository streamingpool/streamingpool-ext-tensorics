/**
 * Copyright (c) 2016 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.streaming.pool.ext.tensorics.streamid;

import org.tensorics.core.commons.operations.Conversion;
import org.tensorics.core.function.DiscreteFunction;

import cern.streaming.pool.core.service.StreamId;

/**
 * A stream id used to transform items of a {@link BufferedStreamId} into a stream of {@link DiscreteFunction}
 * 
 * @author caguiler
 * @param <T> Type of the {@link BufferedStreamId}
 * @param <X> x values of the resulting {@link DiscreteFunction}
 * @param <Y> y values of the resulting {@link DiscreteFunction}
 */
@Deprecated
public class FunctionStreamId<T, X, Y> implements StreamId<DiscreteFunction<X, Y>> {

    private final BufferedStreamId<T> sourceStream;
    private final Conversion<? super T, ? extends X> toX;
    private final Conversion<? super T, ? extends Y> toY;

    /**
     * Builds a {@link FunctionStreamId}
     * 
     * @param sourceStream the {@link BufferedStreamId}
     * @param toX converts T values into X values
     * @param toY converts T values into Y values
     */
    public FunctionStreamId(BufferedStreamId<T> sourceStream, Conversion<? super T, ? extends X> toX,
            Conversion<? super T, ? extends Y> toY) {
        super();
        this.sourceStream = sourceStream;
        this.toX = toX;
        this.toY = toY;
    }

    public BufferedStreamId<T> getSourceStream() {
        return sourceStream;
    }

    public Conversion<? super T, ? extends X> getToX() {
        return toX;
    }

    public Conversion<? super T, ? extends Y> getToY() {
        return toY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sourceStream == null) ? 0 : sourceStream.hashCode());
        result = prime * result + ((toX == null) ? 0 : toX.hashCode());
        result = prime * result + ((toY == null) ? 0 : toY.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FunctionStreamId<?, ?, ?> other = (FunctionStreamId<?, ?, ?>) obj;
        if (sourceStream == null) {
            if (other.sourceStream != null)
                return false;
        } else if (!sourceStream.equals(other.sourceStream))
            return false;
        if (toX == null) {
            if (other.toX != null)
                return false;
        } else if (!toX.equals(other.toX))
            return false;
        if (toY == null) {
            if (other.toY != null)
                return false;
        } else if (!toY.equals(other.toY))
            return false;
        return true;
    }

}
