package net.joaoqalves.nasa.position;

import net.joaoqalves.nasa.exception.RoverIllegalPosition;
import net.joaoqalves.nasa.util.BoundChecker;

/**
 * A {@link Position} represnts a {@link net.joaoqalves.nasa.Rover}
 * position in the game.
 *
 * A {@link Position} is given by the co-ordinate where the {@link net.joaoqalves.nasa.Rover}
 * is and the {@link Direction} where his head is turned to.
 */
public class Position {
    private Coordinate coordinate;
    private Direction direction;

    public Position() {

    }

    public Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (coordinate != null ? !coordinate.equals(position.coordinate) : position.coordinate != null) return false;
        if (direction != null ? !direction.equals(position.direction) : position.direction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coordinate != null ? coordinate.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s", coordinate, direction);
    }

    public void update(Command command, Coordinate upperBound, Coordinate lowerBound)
            throws IllegalArgumentException, RoverIllegalPosition {
        switch(command) {
            case LEFT:
                this.direction = direction.turnLeft();
                break;
            case RIGHT:
                this.direction = direction.turnRight();
                break;
            case MOVE:
                this.coordinate = coordinate.move(direction.moveX(), direction.moveY());
                if(!BoundChecker.check(coordinate, upperBound, lowerBound)) {
                    throw new RoverIllegalPosition("The co-ordinates {" + coordinate.getX() +
                            ", " + coordinate.getY() + "} surpass the limit {" +
                            upperBound.getX() + ", " + upperBound.getY() +"}");
                }
                break;
            default:
                throw new IllegalArgumentException("Commands allowed: L (Left), R (Right) and M (Move)");
        }
    }

}
