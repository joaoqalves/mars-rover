package net.joaoqalves.nasa.io;

import net.joaoqalves.nasa.Mars;
import net.joaoqalves.nasa.Rover;
import net.joaoqalves.nasa.position.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parses a given file to the {@link Mars} system.
 */
public class InputParser {

    private ArrayList<String> input = new ArrayList<String>();

    /**
     * Helper map to convert a compass point from a {@link Character} to a {@link Direction}.
     */
    public static HashMap<Character, Direction> DIRECTIONS = new HashMap<Character, Direction>(){{
        put('E', new CompassPoints.East());
        put('W', new CompassPoints.West());
        put('N', new CompassPoints.North());
        put('S', new CompassPoints.South());
    }};

    /**
     * Helper map to convert a command from a {@link Character} to a {@link Command}.
     */
    public static HashMap<Character, Command> COMMANDS = new HashMap<Character, Command>(){{
       put('L', Command.LEFT);
       put('R', Command.RIGHT);
       put('M', Command.MOVE);
    }};

    /**
     * Reads the given file and initializes the `ìnput` array
     * to be processed later.
     *
     * @param filename the file to be parsed
     * @throws IOException
     */
    public InputParser(String filename) throws IOException {
        String line;
        File file = new File("input.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((line = bufferedReader.readLine()) != null){
            input.add(line);
        }
    }

    /**
     * Parses the `plateau`, the rovers and all the commands.
     *
     * @return a {@link Mars} object, prepared to be explored
     * @throws ArrayIndexOutOfBoundsException
     */
    public Mars parse() throws ArrayIndexOutOfBoundsException {

        // Parse plateau upper bounds
        int upperBoundX = Character.getNumericValue(input.get(0).charAt(0));
        int upperBoundY = Character.getNumericValue(input.get(0).charAt(2));

        Coordinate upperBound = new Coordinate(upperBoundX, upperBoundY);

        // Parse rovers
        ArrayList<Rover> rovers = new ArrayList<Rover>();

        for(int i = 1; i < input.size(); i+= 2) {
            Position p = this.parsePosition((input.get(i)));
            ArrayList<Command> commands =  this.parseCommands(input.get(i + 1));
            rovers.add(new Rover(p, commands));
        }

        return new Mars(rovers, upperBound);

    }

    /**
     * Parses a {@link Rover} in a position in the format "N Y D", where {X, Y} are
     * the co-ordinates and {D} is the Direction € {E, W, N, S}.
     *
     * @param s the current rover position
     * @return a {@link Position} to the rover
     */
    private Position parsePosition(String s) {

        int x = Character.getNumericValue(s.charAt(0));
        int y = Character.getNumericValue(s.charAt(2));
        Character direction = s.charAt(4);

        return new Position(new Coordinate(x, y), DIRECTIONS.get(direction));
    }

    /**
     * Parses a list of commands to a given {@link Rover}.
     *
     * @param s the rover's list of commands
     * @return an {@link ArrayList} of {@link Command}
     */
    private ArrayList<Command> parseCommands(String s) {

        ArrayList<Command> commands = new ArrayList<Command>();

        for(char c: s.toCharArray()) {
            commands.add(COMMANDS.get(c));
        }

        return commands;
    }

}
