package net.joaoqalves.nasa.position;

/**
 * Represents a co-ordinate in the game.
 *
 * Each co-ordinate is represented by a pair {X, Y}, representing
 * the X and Y axes.
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {

    }

    public Coordinate(int x, int y) throws IllegalArgumentException {
        if(x < 0 || y < 0)
            throw new IllegalArgumentException("The co-ordinates X and Y must be greater or equal than 0");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public Coordinate move(int x, int y) {
        return new Coordinate(this.x + x, this.y + y);
    }

}
