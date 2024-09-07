package com.chess.engine.pieces;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import java.util.List;

// Defined the piece class with one abstract method legal moves, and all the pieces we are creating will override
// this and have their own legal behaviour it can take, like a knight and a bishop have very different moves.

// For example, a Knight will be an extension of a piece and so on, implementing the abstract methods, overriding the methods.

public abstract class Piece { // Abstract since there is not only one piece, there are different types of pieces
    // Start by saying that every piece has a piece position,
    // a tile coordinate that is occupied on

    protected final int piecePosition;
    protected final Alliance pieceAlliance; // Whether it is white or black, but can also be using for what player it is

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;

    }

    // Most important method in this class is calculating the legal moves of a piece

    public abstract List<Move> calculateLegalMoves (final Board board);  // This will return a collection of moves, either a set or collection




}
