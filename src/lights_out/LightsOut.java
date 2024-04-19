package lights_out;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LightsOut {

    private int[][] board;
    private final int[][][] pieces;
    private final int depth;
    private List<String> solution;  // List to store the solution using LinkedList


    public LightsOut(
            int depth,
            int[][] board,
            int[][][] pieces) {
        this.depth = depth;
        this.board = board;
        this.pieces = pieces;
        this.solution = new LinkedList<>();
    }

    public boolean solve(int currentPiece) {
        if (currentPiece == pieces.length) {
            return isSolved();
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (placePiece(pieces[currentPiece], row, col)) {
                    solution.add("col - > " + col + " row - > " + row + " ||  pieces -> " + currentPiece);
                    if (solve(currentPiece + 1)) {
                        //System.out.println("col, row -> " + row + "||" + col + " pieces -> " + currentPiece);
                        return true;
                    }
                    solution.remove(solution.size() - 1);
                    /**
                     * We can remove this and just save the last bord, but it is not memory efficient.
                     * and this way it is easire to develop, but we need more time to run the code. this is just a trade-off
                     * between memory and time.
                     *
                     * But we must consider that we in this way we still can face the worst case, so it is not a good idea to think
                     * how we can make this time better, we must think how can we make the memory better in the algorithm.
                     */
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

        // Can we put the piece in the board or not !! ->
        if (xPosition + piece.length > board.length
                || yPosition + piece[0].length > board[0].length) {
            return false;
        }

        // place the piece in the borad - >
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    board[xPosition + i][yPosition + j] = (board[xPosition + i][yPosition + j] + 1) % depth;
                }
            }
        }
        return true;
    }


    private void removePiece(
            int[][] piece,
            int xPosition,
            int yPosition) {

        int pieceLength = piece.length;
        int pieceWidth = piece[0].length;
        int depth = this.depth; // Assuming depth is a field of the class

        // Check if placement would be valid before modifying the board
        int maxX = xPosition + pieceLength;
        int maxY = yPosition + pieceWidth;
        if (maxX > board.length || maxY > board[0].length) {
            return;
        }

        // Loop with pre-calculated increments for better cache locality
        for (int i = 0; i < pieceLength; i++) {
            for (int j = 0; j < pieceWidth; j++) {
                if (piece[i][j] == 1) {
                    board[xPosition + i][yPosition + j] = (board[xPosition + i][yPosition + j] - 1 + depth) % depth;
                }
            }
        }
    }


    private boolean isSolved() {
        int m = board.length; // Get number of rows (m)
        int n = board[0].length; // Get number of columns (n) - assuming all rows have the same length

        // Efficient check for non-zero in the first row
        for (int j = 0; j < n; j++) {
            if (board[0][j] != 0) {
                return false;
            }
        }

        // Early return for single row matrix
        if (m == 1) {
            return true;
        }

        // Check remaining rows for zeros (optimized for locality)
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public void printSolution() {
        for (String move : solution) {
            System.out.println(move);
        }
    }

    public List<String> getSolution() {
        return solution;
    }
}