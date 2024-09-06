public final class OccupiedTile extends Tile {

    Piece pieceOnTile; // Declare the variable which signifies which piece is on the tile

    OccupiedTile(int coordinate, Piece pieceOnTile) {
        super(coordinate); // Super class constructor
        this.pieceOnTile = pieceOnTile; // Construct this variable

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
