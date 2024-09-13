package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.*;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// The king can move in one square in each direction

public class King extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-9,-8,-7-1,1,7,8,9};
    public King(Alliance pieceAlliance, int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>(); // Uses list since because an interface gives you more abstraction
        // Allows you to exchange the implementation at any time

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) { // Going through each offset and determining if that tile is occupied or not
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset; // The destination is the current position add the offset we are applying

            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset ) || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) { // If the piece position and the current offset falls within the we
                continue; // Skip the rest of the code
            }

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance =  pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }


        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9  || candidateOffset == -1 || candidateOffset == 7 ); // These are the inverses of each other
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {// These are the parameters
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9); // These tow exceptions are the inverse of each other
    }
}
