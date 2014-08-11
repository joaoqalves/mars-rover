package net.joaoqalves.nasa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.joaoqalves.nasa.exception.RoverIllegalPosition;
import net.joaoqalves.nasa.position.Coordinate;
import net.joaoqalves.nasa.position.Position;
import net.joaoqalves.nasa.util.BoundChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class of the game.
 *
 * It allows to create a `plateau` M x N and add a list of
 * {@link Rover} to explore the planet.
 *
 * NOTE: It was assumed that the lower bound was (0, 0).
 *
 */
public class Mars {

    /**
     * The list of {@link Rover} to explore the planet
     */
    private List<Rover> rovers;

    /**
     * The lower limit of the `plateau`.
     */
    private Coordinate lowerBound = new Coordinate(0, 0);

    /**
     * The upper limit of the `plateau`.
     */
    private Coordinate upperBound;

    @JsonCreator
    public Mars(@JsonProperty("rovers") ArrayList<Rover> rovers, @JsonProperty("upperBound") Coordinate upperBound) {
        this.rovers = rovers;
        this.upperBound = upperBound;
    }

    /**
     * Iterates through the {@link Rover} list and executes
     * the {@link net.joaoqalves.nasa.position.Command}s.
     *
     * @return the list of {@link Rover} after the exploration
     * @throws RoverIllegalPosition
     */
    public List<Rover> explore() throws RoverIllegalPosition {
        for (Rover rover : this.rovers) {
            rover.execute(upperBound, lowerBound);
        }

        return this.rovers;
    }

    /**
     * Gets a list of {@link Rover} position. Used in the REST API
     *
     * @return the list of {@link Rover} positions
     */
    public List<Position> getRoverPositions() {
        List<Position> positions = new ArrayList<Position>();
        for(Rover rover: this.rovers) {
            positions.add(rover.getPosition());
        }

        return positions;
    }

    public List<Rover> getRovers() {
        return this.rovers;
    }

    public int size() {
        return this.rovers.size();
    }

    public Rover get(int index) {
        return this.rovers.get(index);
    }

    /**
     * Adds a {@link Rover} to the game.
     *
     * @param rover the {@link Rover} to be added.
     * @throws RoverIllegalPosition
     */
    public void add(Rover rover) throws RoverIllegalPosition {

        if(!BoundChecker.check(rover.getPosition().getCoordinate(), upperBound, lowerBound)) {
            throw new RoverIllegalPosition("The co-ordinates {" + rover.getPosition().getCoordinate().getX() +
                    ", " + rover.getPosition().getCoordinate().getY() + "} surpass the limit {" +
                    upperBound.getX() + ", " + upperBound.getY() +"}");
        }

        this.rovers.add(rover);
    }

}
