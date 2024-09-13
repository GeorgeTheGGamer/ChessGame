package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
* Pawn can move two tiles only on its first move, if the tile behind is not occupied
* Pawn captures pieces diagonally
* Pawn can move ahead one space in one direction, if that piece is not taken
* TODO Pawn can get promoted when it reaches the opponents side
* Edge case for the attacking moves, white can't attack left on first column and black can't attack right on the first column
*                                    white can't attack right on the eighth column and black can/t attack left on the eighth column
* TODO Em passant
*/

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

            // Now for this piece, will determine the alliance and then multiply it by the current candidate offset  for
            // the direction of the pawn, if white negative 1 and black positive 1
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
            // This applies to the piece position, but due to directionality, this rule only applies to the black pawns,
            // since the black pawns are moving in the positive 8 directions
            // Need a way to capture directionality, so in the Alliance class will make a method which specifies the direction

            if (!BoardUtils.isValidTileCoordinate(currentCandidateOffset)) {
                continue; // If not within the bounds then skip all the rest of this code
            }

            // When moving one space ahead, if it is not occupied...
            // NOTE: This handles the non-attacking move
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                // TODO More work to do here (Deal with promotions)!!!
                // TODO Come back to to create a new method for this specific task
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                //For moving two spaces, check if it is the first move , check if white or black that it is on specific rows
                // So now we need to initialise the rows too

                // NOTE: This handles the jump move
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) || // If the pawn is on the second row and is black
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) { // If the pawn is on the seventh row and is white
                // Next thing to check is that the square behind the jump we are making is not occupied
                // Since cannot jump ahead of another piece
                final int behindCandiateDestination = this.piecePosition + (this.pieceAlliance.getDirection() * 8); // starting position + 8, in either direction, depending on alliance of the piece
                if (!board.getTile(behindCandiateDestination).isTileOccupied() && // If the behind tile is not occupied
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) { // and the destination tile is not occupied
                    // TODO Come back to to create a new method for this specific task
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate)); // Then we can say that this is a legal move
                }
                // NOTE: This handles the attacking move
            } else if (currentCandidateOffset == 7 &&  // Attacking move to the left for black, right for white
                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) || // If the piece position is on the eighth column and is a white piece, then the 7 attacking move doesn't work
                            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))) { // If the piece position is on the first column and is a black piece, then the 7 attacking move doesn't work
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) { // If the attacking move is occupied in the 7 direction
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece(); // Declare the piece at that destination
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) { // If the moved piece is not the same alliance as the piece on that tile
                        // TODO Come back to to create a new method for this specific task
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate)); // Then add this as a legal attacking move

                    }
                } // Attacking move to the right for black, left for white
            } else if (currentCandidateOffset == 9 &&
                    !(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack() || // If the piece position is on the eighth column and is a black piece, then the attacking move doesn't work
                            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()))) { // If the piece position is on the first column and is a white piece, then the attacking move doesn't work
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) { // If the tile at the destination coordinate is occupied in the 9 direction
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece(); // Then declare the piece that is on that position
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) { // If the moved piece is not the same alliance as the piece on that tile
                        // TODO Come back to to create a new method for this specific task
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate)); // Then add this move as a legal attacking move
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override // Now has a specific ascii value fo the type of piece
    public String toString() {
        return PieceType.PAWN.toString();
    }
}