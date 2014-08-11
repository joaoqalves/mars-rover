package net.joaoqalves.nasa.position;

/**
 * Commands available to the {@link net.joaoqalves.nasa.Rover}.
 *
 * The {@link net.joaoqalves.nasa.Rover} has the following commands available:
 *      - LEFT: makes the {@link net.joaoqalves.nasa.Rover} spin 90º to the left;
 *      - RIGHT: makes the {@link net.joaoqalves.nasa.Rover} spin 90º to the right;
 *      - MOVE: move forward to the next grid point.
 */
public enum Command {
    LEFT, RIGHT, MOVE
}
