package com.chess.engine.pieces;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

// Defined the piece class with one abstract method legal moves, and all the pieces we are creating will override
// this and have their own legal behaviour it can take, like a knight and a bishop have very different moves.

// For example, a Knight will be an extension of a piece and so on, implementing the abstract methods, overriding the methods.

public abstract class Piece { // Abstract since there is not only one piece, there are different types of pieces
    // Start by saying that every piece has a piece position,
    // a tile coordinate that is occupied on

    protected final int piecePosition; // The position of the piece on the board
    protected final Alliance pieceAlliance; // Whether it is white or black, but can also be using for what player it is
    protected final boolean isFirstMove;
    Piece(final int piecePosition, final Alliance pieceAlliance) {

        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        // TODO More work here
        this.isFirstMove = false;

    }

    public int getPiecePosition() { // Getter method for Piece position, visible everywhere
        return this.piecePosition;
    }

    public Alliance getPieceAlliance() { // Getter method for the Alliance of this piece
        return this.pieceAlliance; // Returns the alliance of the piece when called upon
    }


    public boolean isFirstMove() {
        return this.isFirstMove;
    }
    // Most important method in this class is calculating the legal moves of a piece

    // In summary using a set, this cannot have any duplicate entries within it and unordered
    // While a list in ordered, and you can get values at a certain index
    // So now whoever calls this method it is unspecified, it is just a collection
    public abstract Collection<Move> calculateLegalMoves (final Board board);  // This will return a collection of moves, either a set or collection




}
