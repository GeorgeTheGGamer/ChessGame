package com.chess.engine.board;
import com.chess.engine.pieces.Piece;

public final class EmptyTile extends Tile { // Subclass of Tile, final and so cant change
    EmptyTile(int coordinate) { // Create the empty tile constructor
        super(coordinate);  // Since inheriting the methods from the tile class, the coordinate was already declared so
                           // use super to access the super class

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
