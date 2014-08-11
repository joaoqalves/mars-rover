package net.joaoqalves.nasa.util;

import net.joaoqalves.nasa.position.Coordinate;

/**
 * Helper class to check a given position is between the
 * bounds of the `plateau`.
 */
public class BoundChecker {

    /**
     * Checks if a given co-ordinate is inside the `plateau`.
     *
     * @param c1 the co-ordinate to check
     * @param c2 the upper bound co-ordinate of the `plateau`
     * @param c3 the lower bound co-ordinate of the `plateau`
     * @return true if the {@link net.joaoqalves.nasa.Rover} is inside the `plateau`,
     * false otherwise
     */
    public static Boolean check(Coordinate c1, Coordinate c2, Coordinate c3) {
        return !(c1.getX() > c2.getX() ||
                 c1.getY() > c2.getY() ||
                 c1.getX() < c3.getX() ||
                 c1.getY() < c3.getY());
    }

}
