package net.joaoqalves;

import net.joaoqalves.nasa.Mars;
import net.joaoqalves.nasa.Rover;
import net.joaoqalves.nasa.exception.RoverIllegalPosition;
import net.joaoqalves.nasa.io.InputParser;

import java.io.*;
import java.util.ArrayList;

/**
 * The `main` class to execute the game.
 */
public class App 
{
    public static void main( String[] args ) throws IOException, RoverIllegalPosition {

        InputParser i = new InputParser("input.txt");
        Mars mars = i.parse();

        for (Rover rover : mars.explore()) {
            System.out.println(rover.getPosition().toString());
        }
    }
}
