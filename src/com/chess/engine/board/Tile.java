/*Efficiency: By caching all possible empty tiles, the code avoids repeatedly creating new empty tile objects,
which can save memory and improve performance.

Immutability: The EMPTY_TILES map is final and cannot be modified after it’s created, ensuring that the empty tiles
remain consistent throughout the game’s lifecycle.*/

package com.chess.engine.board;
import com.chess.engine.pieces.Piece;

import java.util.Collections; // Fo the unmodifiable map, use instead of ImmutableMap, using the guava library
import java.util.HashMap;
import java.util.Map; // Interface that maps keys to values
// Chess Board has 64 tiles

// Abstract class - "Tile" is too vague and so make abstract, also adds extra layer of security
// Meaning that because this class is abstract we cannot instantiate it but can instantiate its
//  subclasses as long as they implement the abstract classes methods. So not a concrete method
// So cant say Tile tile = new Tile();, must instantiate a concrete subclass of it

public abstract class Tile { // Parent class of EmptyTile

    // Introducing a member field which represents the tile number
    protected final int tileCoordinate; // Make protected so can only be accessed by the subclasses, anf final so can't change

    // t’s marked as private static final, meaning it’s a constant that belongs to the class and cannot be modified after initialization
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles(); // initializes a map (EMPTY_TILES) that holds all possible empty tiles.

    // With this method it has constructed every possible empty tile so that it doesn't have to be constructed again
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        /*This method constructs a map of empty tiles, with each tile corresponding to a unique coordinate
        (0 to 63, representing an 8x8 board). Each tile is created once and stored in the map*/
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i=0; i<BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return Collections.unmodifiableMap(emptyTileMap); // After Constructed don't want anyone to be able to change it.
    }

    // Only way to create a new tile is by this new factory method
    public static Tile createTile(final int tileCoordinate, final Piece piece) {

        // If they want an empty tile they will get one of the cached empty tiles otherwise create a new occupied tile
        /*This method is used to create a tile at a given coordinate. If a piece is provided, it creates an OccupiedTile
         with that piece. If no piece is provided (piece == null), it returns one of the pre-created empty tiles from the
         EMPTY_TILES map.*/

        // If the piece is not null, create a new occupied tile at the coordinate and its piece
        // Otherwise create an empty tile that we retrieve from the empty tile cache
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    private Tile(final int tileCoordinate) { // Only accessible in this class
        this.tileCoordinate = tileCoordinate; // When constructing a new tile, it will be assigned a tile coordinate
    }

    // Class is abstract, so is not defined in this class but in a subclass, no body required
    public abstract boolean isTileOccupied(); // Determines whether or not a given tile is occupied or not

    public abstract Piece getPiece();


    // *************************************************************************************************
    // NOW to create the subclasses which will represent either an empty tile or an occupied tile
    // *************************************************************************************************



    public static final class OccupiedTile extends Tile { // Child class from Tile and so inherits its unimplemented methods

        // This is the field that can be mutated so make final since we don't want it to change
        // And make class private - No way to reference this variable outside without a getter
        private final Piece pieceOnTile; // Declare the variable which signifies which piece is on the tile

        private OccupiedTile(int coordinate, final Piece pieceOnTile) {
            super(coordinate); // Super class constructor
            this.pieceOnTile = pieceOnTile; // Construct this variable

        }

        @Override
        public String toString() {
            // If black return as lowercase, if white return as upper case
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                    getPiece().toString();
        }
        // Since abstract, implement unimplemented methods and override
        @Override
        public boolean isTileOccupied() {
            return true; // True since tile is occupied
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile; // Return "this" piece, meaning whichever piece is on the tile
        }
    }

    public static final class EmptyTile extends Tile { // Subclass of Tile, final and so cant change
        private EmptyTile(final int coordinate) { // Create the empty tile constructor, make final so can't be changed on initialisation
            super(coordinate);  // Since inheriting the methods from the tile class, the coordinate was already declared so
            // use super to access the super class

        }

        @Override
        public String toString() { // For an empty tile, output this
            return "-";
        }

        @Override // Since Method is abstract, must implement unimplemented methods
        public boolean isTileOccupied() {
            return false; // Returns false, since the tile is not occupied
        }

        @Override // Since Method is abstract, must implement unimplemented methods
        public Piece getPiece() {
            return null; // By definition, will return null since no piece on empty tile to return
        }

    }


}

/*

* In this parent class and the subsequent subclasses we have defined a chess tile
* Takes as a parameter a chess tile coordinate, which is established
* Key methods are whether the tile is occupied or empty
* and to retrieve the piece on the tile
* Have to subclasses which define those behaviours, abstract parent class since tile is too vague

* */
