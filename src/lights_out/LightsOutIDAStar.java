package lights_out;

import lights_out.record.Move;
import lights_out.record.State;

import java.util.*;

public class LightsOutIDAStar {
    private int[][] board;
    private final int[][][] pieces;
    private final int depth;
    private final int rows;
    private final int cols;

    public LightsOutIDAStar(
            int depth,
            int[][] board,
            int[][][] pieces) {
        this.depth = depth;
        this.board = board;
        this.pieces = pieces;
        this.rows = board.length;
        this.cols = board[0].length;
    }

    /**
     * Iterative Deepening A*
     **/
    public boolean solve() {
        int threshold = calculateHeuristic(board);
        while (true) {
            int temp = search(
                    new State(cloneBoard(board),
                            new ArrayList<>(),
                            0),
                    0, // cost
                    threshold);
            if (temp == -1) return true; // we find the solution
            if (temp == Integer.MAX_VALUE) return false; // we didnt found any solution
            threshold = temp; // Increase threshold
        }
    }

    // IDA* search
    private int search(
            State node,
            int g, //cost
            int threshold) {
        int f = g + calculateHeuristic(node.board()); // estimated cost of the cheapest solution for the node.
        if (f > threshold) return f;  // maximum allowed cost f for a path
        if (isSolved(node.board())) {
            printSolution(node.moves());
            return -1; // Found solution
        }

        int min = Integer.MAX_VALUE;
        int nextPieceIndex = node.moves().size() < pieces.length ? node.moves().size() : 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int[][] newBoard = cloneBoard(node.board());
                if (placePiece(newBoard, pieces[nextPieceIndex], row, col)) {
                    List<Move> newMoves = new ArrayList<>(node.moves());
                    newMoves.add(new Move(nextPieceIndex, row, col));
                    int temp = search(new State(newBoard, newMoves, g + 1), g + 1, threshold);
                    if (temp == -1) return -1;
                    if (temp < min) min = temp;
                }
            }
        }
        return min;
    }
    private boolean isSolved(int[][] b) {
        for (int[] row : b) {
            for (int value : row) {
                if (value != 0) return false;
            }
        }
        return true;
    }

    private int calculateHeuristic(int[][] b) {
        int count = 0;
        for (int[] row : b) {
            for (int value : row) {
                if (value != 0) count++;
            }
        }
        return count;
    }

    private int[][] cloneBoard(int[][] original) {
        return Arrays.stream(original).map(int[]::clone).toArray(int[][]::new);
    }

    private boolean placePiece(int[][] b, int[][] piece, int startX, int startY) {
        if (startX + piece.length > b.length || startY + piece[0].length > b[0].length) {
            return false; // Out of bounds
        }

        boolean placed = false;
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    b[startX + i][startY + j] = (b[startX + i][startY + j] + 1) % depth;
                    placed = true;
                }
            }
        }

        return placed;
    }

    // Print the solution moves
    private void printSolution(List<Move> moves) {
        moves.forEach(move -> System.out.println(move));
    }


}
