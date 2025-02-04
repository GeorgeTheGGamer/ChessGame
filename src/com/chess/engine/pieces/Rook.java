package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import static com.chess.engine.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {

    private static final int[] CANDIDATE_VECTOR_OFFSETS = {-8,-1,1,8};
    public Rook(final Alliance pieceAlliance,final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateDestinationOffset : CANDIDATE_VECTOR_OFFSETS) {
            int candidateDestinationCoordinate = this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate,candidateDestinationOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinate,candidateDestinationOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateDestinationOffset;
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                    } else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));

                        }
                    }
                }

            }
        }

        return Collections.unmodifiableList(legalMoves);
    }

    @Override // Now has a specific ascii value fo the type of piece
    public String toString() {
        return PieceType.ROOK.toString();
    }

    // Edge cases to consider

    // When on the first column the -1 values fall apart
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);

    }

    // When on the first column the positive 1 values fall apart
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 1);

    }
}
