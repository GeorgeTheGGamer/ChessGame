package com.chess.engine.board;

// This is a utility class to house the useful constants and methods which are static
// static members can be accessed without creating an object of the class, and they are shared across all instances of the class.
public class BoardUtils {

    // List of booleans for each tile in the first tile of the chess board all true
    // Then all other tiles will be set to false because we're only caring about the first column currently
    public static final boolean[] FIRST_COLUMN = initColumn(0); // Call a method which parsing in the column number
    public static final boolean[] SECOND_COLUMN = initColumn(1); // initColumn = initialise column
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final int NUM_TILES = 64; // Allocates memory for this singular value and now can be repeated in whole program
    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate this!!!"); // Prevents from instantiating this class
    }

    private static boolean[] initColumn(int columnNumber) { // Can't be final because we use this local variable, we mutate the variable
        final boolean[] column = new boolean[NUM_TILES]; // Making a boolean array of size 64, from 0-63
        // Takes a single parameter of column number, when you pass in for example column of 0, that is the first column
        // And so sets the column to true
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW; // Then will add 8 and say that is equal to true, then add 8 again
        } while(columnNumber < NUM_TILES);

        return column;
    }

    public static boolean isValidTileCoordinate(final int coordinate) { // Now public because useful to more than just the knight class
        return coordinate >= 0 && coordinate < NUM_TILES; // So within the bounds of the board tiles
    }
}
