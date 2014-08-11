package net.joaoqalves.nasa.exception;

/**
 * The class {@link net.joaoqalves.nasa.exception.RoverIllegalPosition} should be
 * thrown when a {@link net.joaoqalves.nasa.Rover} is put in an illegal position, i.e.,
 * out of the plateau bounds.
 */
public class RoverIllegalPosition extends Exception {

    public RoverIllegalPosition() {

    }

    public RoverIllegalPosition(String message) {
        super(message);
    }
}
