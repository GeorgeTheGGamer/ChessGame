package com.chess.engine.board;

/*
* The build() method of java.util.Locale.Builder class in Java is used to build a Locale from the values
* specified to this Locale.Builder instance. This method returns a Locale instance after building it.
* */

import com.chess.engine.Alliance;
import com.chess.engine.pieces.*;

import java.util.*;

import static com.chess.engine.board.BoardUtils.*;

public class Board {

    // Introduction of member fields:
    // List allows for duplicates
    // Collections don't allow for duplicates, and for users to index through the list
    private final List<Tile> gameBoard;// Collection of tiles, can have immutable list, cannot have an immutable array
    private final Collection<Piece> whitePieces; // Keeps track the white pieces on the board
    private final Collection<Piece> blackPieces; // Keeps track of the black pieces on the board

    private Board(Builder builder) { // Private constructor prevents class instances from being created in any other place other than this very class
        // Your class instances are created in a static method. The static method is then declared as public.

        this.gameBoard = createGameBoard(builder); // Access this method from the builder class
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE); // For this game board, calculate the white pieces
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK); // For this game board, calculate the black pieces

        // Declare Local Variables
        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

    }

    // Outputs the board as an ascii table, conveniently
    @Override // Overriding the base toString method , Prints out our board
    public String toString() {
        final StringBuilder builder = new StringBuilder(); // Instantiate string builder class
        for (int i = 0; i < NUM_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i+1) % NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }

        }
        return builder.toString();
    }

    // *****************************************************************************************************************************
    //                          Methods used to flesh out the white and the black player
    private Collection<Move> calculateLegalMoves(Collection<Piece> Pieces) {
        final List<Move> legalMoves = new ArrayList<>(); // Declare a list of legal moves
        for (final Piece piece : Pieces) { // Loop through each piece
            // This container holds all the legal moves on the board for every piece of the specified alliance
            legalMoves.addAll(piece.calculateLegalMoves(this)); // Takes in a board which we're on so use the "this" pointer
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard,
                                                           final Alliance alliance) {
        // This tracks the black and the white active pieces
        final List<Piece> activePieces = new ArrayList<>(); // Initialise a List
        for (final Tile tile : gameBoard) { // Loop through each tile on the game board
            if(tile.isTileOccupied()) { // If the tile is occupied
                final Piece piece = tile.getPiece(); // Declare a Piece as the piece on that tile
                if(piece.getPieceAlliance() == alliance) { // if piece on that tile is equal to the passed in alliance
                    activePieces.add(piece); // Then add the piece on that tile to the active pieces list
                }
            }
        }
        return Collections.unmodifiableList(activePieces); // Return an immutable list
    }

    // *****************************************************************************************************************************

    public Tile getTile(final int tileCoordinate) {
        return gameBoard.get(tileCoordinate); // Getter for the tile at a specific position on the game board
    }

    // This method populates a list of tiles numbered 0 to 63, this represents our chess board
    public static List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[NUM_TILES]; // Makes a board of size 64 tiles
        for (int i = 0; i<NUM_TILES; i++) { // With in this loop you go through and get from the config you will map a piece to that tile ID
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i)); // When you create tile, you get the position of the board and then the piece at that position of the board
        }
        return Collections.unmodifiableList(Arrays.asList(tiles)); // Return an immutable list
    }

    public static Board createStandardBoard() { // This creates the initial position for a chess board
        // Uses the builder class to create a new standard chess board
        final Builder builder = new Builder(); // Instantiate new builder
        // Black Layout
        builder.setPiece(new Rook(Alliance.BLACK,0));
        builder.setPiece(new Knight(Alliance.BLACK,1));
        builder.setPiece(new Bishop(Alliance.BLACK,2));
        builder.setPiece(new Queen(Alliance.BLACK,3));
        builder.setPiece(new King(Alliance.BLACK,4));
        builder.setPiece(new Bishop(Alliance.BLACK,5));
        builder.setPiece(new Knight(Alliance.BLACK,6));
        builder.setPiece(new Rook(Alliance.BLACK,7));
        builder.setPiece(new Pawn(Alliance.BLACK,8));
        builder.setPiece(new Pawn(Alliance.BLACK,9));
        builder.setPiece(new Pawn(Alliance.BLACK,10));
        builder.setPiece(new Pawn(Alliance.BLACK,11));
        builder.setPiece(new Pawn(Alliance.BLACK,12));
        builder.setPiece(new Pawn(Alliance.BLACK,13));
        builder.setPiece(new Pawn(Alliance.BLACK,14));
        builder.setPiece(new Pawn(Alliance.BLACK,15));
        
        //White Layout
        builder.setPiece(new Pawn(Alliance.WHITE,48));
        builder.setPiece(new Pawn(Alliance.WHITE,49));
        builder.setPiece(new Pawn(Alliance.WHITE,50));
        builder.setPiece(new Pawn(Alliance.WHITE,51));
        builder.setPiece(new Pawn(Alliance.WHITE,52));
        builder.setPiece(new Pawn(Alliance.WHITE,53));
        builder.setPiece(new Pawn(Alliance.WHITE,54));
        builder.setPiece(new Pawn(Alliance.WHITE,55));
        builder.setPiece(new Rook(Alliance.WHITE,56));
        builder.setPiece(new Knight(Alliance.WHITE,57));
        builder.setPiece(new Bishop(Alliance.WHITE,58));
        builder.setPiece(new Queen(Alliance.WHITE,59));
        builder.setPiece(new King(Alliance.WHITE,60));
        builder.setPiece(new Bishop(Alliance.WHITE,61));
        builder.setPiece(new Knight(Alliance.WHITE,62));
        builder.setPiece(new Rook(Alliance.WHITE,63));

        // White Moves First
        builder.setMoveMaker(Alliance.WHITE);

        return builder.build(); // Creates a new immutable board of the starting position

        // Whenever a piece is not set, we call the create game board builder, if there's
        // No piece on that tile, then return piece is null, as in empty, and retrieve from
        // the empty tile cache

    }

    // Time to create a board with pieces on it
    public static class Builder { // The Builder is an interface or an abstract class that declares the construction steps for building a complex object.

        /*
        It typically includes methods for constructing individual parts of the product.
        By defining an interface, the Builder allows for the creation of different concrete
        builders that can produce variations of the product.*/

        // Each tile on a chess board has an ID
        Map<Integer, Piece> boardConfig; // Will match a tile ID to a given piece on that tile ID
        Alliance nextMoveMaker; // The Person to move, person whose turn it is o move on the given board

        public Builder() { // Expose the builder constructor as public
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece(final Piece piece) { // Set the piece on the builder
            this.boardConfig.put(piece.getPiecePosition(), piece); // Setting the property of the current builder
            return this; // And then returning that builder back to where it was called from
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker) { // Set the colour whose move it is
            this.nextMoveMaker = nextMoveMaker; // Setting the property of the current builder
            return this; // And then returning that builder back to where it was called from
        }

        // We will set mutable fields on the builder and then once build is invoked, it will create an immutable board
        public Board  build() {
            return new Board(this); // An immutable board that cannot be changed
        }
    }
}
