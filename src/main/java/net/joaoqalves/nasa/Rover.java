package net.joaoqalves.nasa;

import net.joaoqalves.nasa.exception.RoverIllegalPosition;
import net.joaoqalves.nasa.position.Command;
import net.joaoqalves.nasa.position.Coordinate;
import net.joaoqalves.nasa.position.Position;
import java.util.ArrayList;

/**
 * The {@link Rover}. It is defined by its {@link Position} and a list
 * of {@link Command}s to be executed.
 */
public class Rover {

    private Position position;
    private ArrayList<Command> commands;

    public Rover() {

    }

    public Rover(Position position, ArrayList<Command> commands) {
        this.position = position;
        this.commands = commands;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Executes the {@link Command}s of a given {@link Rover}.
     *
     * @param upperBound the upper bounds of the `plateau`
     * @param lowerBound the lower bounds of the `plateau`
     *
     * @throws RoverIllegalPosition
     */
    public void execute(Coordinate upperBound, Coordinate lowerBound) throws RoverIllegalPosition {
        for(Command command: commands) {
            this.position.update(command, upperBound, lowerBound);
        }
    }

}
