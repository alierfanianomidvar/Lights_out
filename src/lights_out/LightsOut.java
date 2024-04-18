package lights_out;

public class LightsOut {

    private int[][] board;
    private final int[][][] pieces;
    private final int depth;

    public LightsOut(
            int depth,
            int[][] board,
            int[][][] pieces) {
        this.depth = depth;
        this.board = board;
        this.pieces = pieces;
    }

    public boolean solve(int currentPiece) {
        if (currentPiece == pieces.length) {
            return isSolved();
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

    /**
     * We can remove this and just save the last bord, but it is not memory efficient.
     * and this way it is easire to develop, but we need more time to run the code. this is just a trade-off
     * between memory and time.
     *
     * But we must consider that we in this way we still can face the worst case, so it is not a good idea to think
     * how we can make this time better, we must think how can we make the memory better in the algorithm.
     */
    private void removePiece(
            int[][] piece,
            int xPosition,
            int yPosition) {

        // Can we place the piece in the board !! - > here
        if (xPosition + piece.length > board.length
                || yPosition + piece[0].length > board[0].length) {
            return;
        }

        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    // remove the piece from the board.
                    board[xPosition + i][yPosition + j] = (board[xPosition + i][yPosition + j] - 1 + depth) % depth;
                }
            }
        }
    }

    private boolean isSolved() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    return false; // If we saw non zero value, we did not solve the problem.
                }
            }
        }
        return true;
    }


}
