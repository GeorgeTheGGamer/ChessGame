package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move { // Abstract since there are different types of move

    final Board board; // Keep track of the board that we are traversing
    final Piece movedPiece; // Keep track of the piece which is moving
    final int destinationCoordinate; // Keep track of where that piece moves


    private Move(final Board board,
         final Piece movedPiece,
         final int destinationCoordinate) {

        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    // We are wanting ot tell the difference between an attacking move and a non-attacking move
    public static final class MajorMove extends Move { // As in a major piece move
        public MajorMove(final Board board, // This is a move to an empty tile
                  final Piece movedPiece,
                  final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {

        // In an attack move there is one more value that you want to keep track of, the attacked piece
        final Piece attackedPiece;

        public AttackMove(final Board board, // This is taking another piece when moving
                   final Piece movedPiece,
                   final int destinationCoordinate,
                   final Piece attackedPiece ) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }
}
