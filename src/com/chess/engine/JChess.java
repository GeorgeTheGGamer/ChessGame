package com.chess.engine;

import com.chess.engine.board.Board;

public class JChess {
    public static void main(String[] args) {


        // Test to see if the ascii staring table outputs
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }
}
