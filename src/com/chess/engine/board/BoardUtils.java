package com.chess.engine.board;

// This is a utility class to house the useful constants and methods which are static
// static members can be accessed without creating an object of the class, and they are shared across all instances of the class.
public class BoardUtils {

    // List of booleans for each tile in the first tile of the chess board all true
    // Then all other tiles will be set to false because we're only caring about the first column currently
    public static final boolean[] FIRST_COLUMN = null; // Placeholder for now
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    public static final boolean[] EIGHTH_COLUMN = null;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate this!!!"); // Prevents from instantiating this class
    }
    public static boolean isValidTileCoordinate(int coordinate) { // Now public because useful to more than just the knight class
        return coordinate >= 0 && coordinate < 64; // So within the bounds of the board tiles
    }
}
