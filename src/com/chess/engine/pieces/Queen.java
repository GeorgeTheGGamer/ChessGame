package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// Mix of the Rook and the Bishop

public class Queen extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-8,-7,-1,1,7,8,9};


    Queen(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition; // For now takes on the piece position
            // Keeps on adding the offset until it is out of the bounds, when out of the bounds, the program then moves
            // to the next offset and then loops again until the bishop cant continue
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { // While within the bounds of the board

                if(isFirstColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)) {
                    break;
                }

                candidateDestinationCoordinate += candidateCoordinateOffset; // This then applies the offset to the current position
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { // If the piece is still with in the board
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance) { // If piece on the  tile is different colour than the bishop
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break; // If the tile is not occupied, you want the bishop to keep goin gbut if it is occupied
                        // you want the bishop to take that piece and stop it in its tracks
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    // Edge cases to consider

    // When on the first column the -9 and positive 7 values fall apart
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7 || candidateOffset == -1);
    }
    // When on the eighth column the -7 and positive 9 values fall apart
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9 || candidateOffset == 1);
    }
}
