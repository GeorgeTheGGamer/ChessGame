package com.chess.engine.pieces;

import com.chess.engine.Alliance; // Importing the enum, either white or black alliance
import com.chess.engine.board.Board; // Importing board
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece{ // Knight inherits the Piece class

    // There are 8 current offsets from the position of where the knight can go, for the tiles, can manipulate these
    // numbers to its new starting point, but will always be the right tile away from it
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17,-15,-6,6,10,15,17}; // A knight at most on a move can have 8 moves


    Knight(final int piecePosition, final Alliance alliance) {
        super(piecePosition,alliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board){

        // Will use a list but the caller of this method will not be able to rely on hat behaviour
        final List<Move> legalMoves = new ArrayList<>(); // List of all the legal moves for this piece

        // Will loop through all the candidate locations
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) { // Looping through each value in the array

            // Declare new variable which holds the move for the piece which is the knight in this case
            // Applying the offset, whether positive or negative to the current position
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset; // Position value

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { // If the destination is within the bounds of the chess board

                // Declare new variable which uses the tile class, and gets the tile from the candidate destination
                // coordinate on the board
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                // These tackle the exceptions of the knight position for the specific columns
                if (    (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)) ||
                        (isSecondColumnExclusion(this.piecePosition, currentCandidateOffset)) ||
                        (isSeventhColumnExclusion(this.piecePosition,currentCandidateOffset)) ||
                        (isEighthColumnExclusion(this.piecePosition, currentCandidateOffset))
                    ) {
                    continue; // Used to skip the rest of the loop, because this move cannot be legal
                }

                // Now that we have that specific tile
                if (!candidateDestinationTile.isTileOccupied()) {
                    // This is a neutral move
                    legalMoves.add(new Move()); // Placeholder for now

                } else {

                }
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece(); // Retrieves the piece on square
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance(); // Retrieves alliance of that piece

                    if (this.pieceAlliance != pieceAlliance) {// Signifies that the piece on that coordinate is an enemy piece
                        // This is an attacking move
                        legalMoves.add(new Move()); // Adds this as a legal move to the list
                }
            }

        }
        return Collections.unmodifiableList(legalMoves); // Cannot be changed after initialisation
    }

    // It turns out that when on the edges of the board, this notion breaks down and the moves are not legal

    // All edge cases ran into if the knight were sitting on the first column of the chess board
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        // Introduce an array of booleans in board utils, this array we're going to call first column
        // If the current position falls within the current position of the chess board and the offset is equal to
        // any of the given values
        // More notes on FIRST_COLUMN in the board utils class
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) ||
                                                            (candidateOffset == 6) || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) { // Any cases on the second column which fails
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) ||
                                                            (candidateOffset == 10) || (candidateOffset == 17));
    }

}
