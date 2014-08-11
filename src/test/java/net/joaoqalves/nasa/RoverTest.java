package net.joaoqalves.nasa;

import junit.framework.TestCase;
import net.joaoqalves.nasa.exception.RoverIllegalPosition;
import net.joaoqalves.nasa.position.Command;
import net.joaoqalves.nasa.position.CompassPoints;
import net.joaoqalves.nasa.position.Coordinate;
import net.joaoqalves.nasa.position.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit tests for the {@link Rover} class.
 *
 */
public class RoverTest extends TestCase {

    Coordinate UPPER_BOUND = new Coordinate(5, 5);
    Coordinate LOWER_BOUND = new Coordinate(0, 0);
    Position TEST_POSITION = new Position(new Coordinate(1, 1), new CompassPoints.East());

    public void testRotateLeft() throws RoverIllegalPosition {

        Position EXPECTED_POSITION1 = new Position(new Coordinate(1, 1), new CompassPoints.North());
        Position EXPECTED_POSITION2 = new Position(new Coordinate(1, 1), new CompassPoints.West());
        Position EXPECTED_POSITION3 = new Position(new Coordinate(1, 1), new CompassPoints.South());

        ArrayList<Command> commands = new ArrayList<>();
        commands.add(Command.LEFT);
        Rover r = new Rover(TEST_POSITION, commands);

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION1, r.getPosition());

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION2, r.getPosition());

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION3, r.getPosition());

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(TEST_POSITION, r.getPosition());

    }

    public void testRotateRight() throws RoverIllegalPosition {

        Position EXPECTED_POSITION1 = new Position(new Coordinate(1, 1), new CompassPoints.South());
        Position EXPECTED_POSITION2 = new Position(new Coordinate(1, 1), new CompassPoints.West());
        Position EXPECTED_POSITION3 = new Position(new Coordinate(1, 1), new CompassPoints.North());

        ArrayList<Command> commands = new ArrayList<>();
        commands.add(Command.RIGHT);
        Rover r = new Rover(TEST_POSITION, commands);

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION1, r.getPosition());

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION2, r.getPosition());

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION3, r.getPosition());

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(TEST_POSITION, r.getPosition());

    }

    public void testRotateAndMove() throws RoverIllegalPosition {

        Position EXPECTED_POSITION1 = new Position(new Coordinate(1, 1), new CompassPoints.North());
        Position EXPECTED_POSITION2 = new Position(new Coordinate(1, 2), new CompassPoints.North());
        Position EXPECTED_POSITION3 = new Position(new Coordinate(1, 3), new CompassPoints.North());

        ArrayList<Command> commands = new ArrayList<>();
        commands.add(Command.LEFT);
        Rover r = new Rover(TEST_POSITION, commands);

        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION1, r.getPosition());

        commands.clear();
        commands.add(Command.MOVE);
        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION2, r.getPosition());

        commands.clear();
        commands.add(Command.MOVE);
        r.execute(UPPER_BOUND, LOWER_BOUND);
        assertEquals(EXPECTED_POSITION3, r.getPosition());
    }

    public void testIllegalPosition() {
        ArrayList<Command> commands = new ArrayList<>(Arrays.asList(Command.MOVE, Command.MOVE, Command.MOVE, Command.MOVE, Command.MOVE));
        Rover r = new Rover(TEST_POSITION, commands);

        try {
            r.execute(UPPER_BOUND, LOWER_BOUND);
            fail("Should not reach this");
        }
        catch(RoverIllegalPosition ex) {
            assertEquals(RoverIllegalPosition.class, ex.getClass());
        }

    }

}
