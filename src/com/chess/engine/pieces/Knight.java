package com.chess.engine.pieces;

import com.chess.engine.Alliance; // Importing the enum, either white or black alliance
import com.chess.engine.board.Board; // Importing board
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import java.util.ArrayList;
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
    public List<Move> calculateLegalMoves(Board board){

        int candidateDestinationCoordinate; // Declare new variable which holds the move for the piece which is the knight in this case
        final List<Move> legalMoves = new ArrayList<>(); // List of all the legal moves for this piece

        // Will loop through all the candidate locations
        for(final int currentCandidate : CANDIDATE_MOVE_COORDINATES) { // Looping through each value in the array

            // Applying the offset, whether positive or negative to the current position
            candidateDestinationCoordinate = this.piecePosition + currentCandidate; // Position value

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { // If the destination is within the bounds of the chess board

                // Declare new variable which uses the tile class, and gets the tile from the candidate destination
                // coordinate on the board
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

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
        // If the current position falls within the current position of the chess board and the offset
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || candidateOffset == 15);
    }

}
