package lights_out;

import java.util.HashMap;
import java.util.Map;

public class LightsOut {

    private int[][] board;
    private final int[][][] pieces;
    private final int depth;

    private int[] boardState;

    private Map<BoardState, Boolean> memo = new HashMap<>();

    public LightsOut(
            int depth,
            int[][] board,
            int[][][] pieces) {
        this.depth = depth;
        this.board = board;
        this.pieces = pieces;
        initializeBoardState();
    }

    public boolean solve(int currentPiece) {

        BoardState boardKey = new BoardState(boardState);
        if (memo.containsKey(boardKey)) {
            return memo.get(boardKey);
        }

        if (currentPiece == pieces.length) {
            boolean solved = isSolved();
            memo.put(boardKey, solved);
            return solved;
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (placePiece(pieces[currentPiece], row, col)) {
                    if (solve(currentPiece + 1)) {
                        System.out.println("row, col -> " + row + "||" + col + " pieces -> " + currentPiece);
                        return true;
                    }
                    removePiece(pieces[currentPiece], row, col);
                }
            }
        }
        return false;
    }

    private boolean placePiece(
            int[][] piece,
            int xPosition,
            int yPosition) {

        if (xPosition + piece.length > board.length || yPosition + piece[0].length > board[0].length) {
            return false;
        }

        for (int i = 0; i < piece.length; i++) {
            int boardX = xPosition + i;
            if (boardX >= board.length) continue;  // to be sure that we do not go out of x bounds
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    int bitPos = yPosition + j;
                    if (bitPos >= board[0].length * bitsPerCell())
                        continue; // to be sure that we do not go out of y bounds
                    boardState[boardX] ^= (1 << bitPos);  // toggle the bit
                }
            }
        }
        return true;
    }


    private void removePiece(int[][] piece, int xPosition, int yPosition) {
        if (xPosition + piece.length > board.length || yPosition + piece[0].length > board[0].length) {
            return;
        }
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    int bitPos = yPosition + j;
                    boardState[xPosition + i] ^= (1 << bitPos);  // Toggle the bit back
                }
            }
        }
    }

    private boolean isSolved() {
        for (int state : boardState) {
            if (state != 0) return false;  // Check if all bits are 0
        }
        return true;
    }

    private void initializeBoardState() {
        boardState = new int[board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardState[i] |= (board[i][j] << (j * bitsPerCell()));
            }
        }
    }

    private int bitsPerCell() {
        return (int) (Math.log(depth) / Math.log(2));
    }

    private String boardToString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell);
            }
        }
        return sb.toString();
    }

}
