package net.joaoqalves.nasa.position;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Generic Interface to each {@link Direction}.
 *
 * Each {@link Direction}:
 * {@link net.joaoqalves.nasa.position.CompassPoints.East},
 * {@link net.joaoqalves.nasa.position.CompassPoints.West},
 * {@link net.joaoqalves.nasa.position.CompassPoints.North} and
 * {@link net.joaoqalves.nasa.position.CompassPoints.South}
 * must implement the methods to turn the {@link net.joaoqalves.nasa.Rover}
 * to the left, right and to move it both in the X and Y axes.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CompassPoints.East.class, name = "E"),
        @JsonSubTypes.Type(value = CompassPoints.West.class, name = "W"),
        @JsonSubTypes.Type(value = CompassPoints.North.class, name = "N"),
        @JsonSubTypes.Type(value = CompassPoints.South.class, name = "S")})
public interface Direction {

    public Direction turnLeft();
    public Direction turnRight();

    public int moveX();
    public int moveY();

    public String toString();

}
