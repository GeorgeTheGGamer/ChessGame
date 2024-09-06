
// Chess Board has 64 tiles
// Abstract class - "Tile" is too vague and so make abstract, also adds extra layer of security
public abstract class Tile { // Parent class of EmptyTile

    // Introducing a member field which represents the tile number
    int tileCoordinate;

    Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate; // When constructing a new tile, it will be assigned a tile coordinate
    }

    // Class is abstract, so is not defined in this class but in a subclass, no body required
    public abstract boolean isTileOccupied(); // Determines whether or not a given tile is occupied or not

    public abstract Piece getPiece();


    // NOW to create the subclasses which will represent either an empty tile or an occupied tile

}

/*

* In this parent class and the subsequent subclasses we have defined a chess tile
* Takes as a parameter a chess tile coordinate, which is established
* Key methods are whether the tile is occupied or empty
* and to retrieve the piece on the tile
* Have to subclasses which define those behaviours, abstract parent class since tile is too vague
* */
