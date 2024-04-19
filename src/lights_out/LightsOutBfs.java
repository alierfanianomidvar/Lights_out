package lights_out;

import lights_out.record.Move;
import lights_out.record.State;

import java.util.*;


public class LightsOutBfs {
    private int[][] board;
    private final int[][][] pieces;
    private final int depth;
    private final int rows;
    private final int cols;

    public LightsOutBfs(int depth, int[][] board, int[][][] pieces) {
        this.depth = depth;
        this.board = board;
        this.pieces = pieces;
        this.rows = board.length;
        this.cols = board[0].length;
    }

    // BFS solution method
    public boolean solve() {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        State initial = new State(cloneBoard(board), new ArrayList<>(), 0);
        queue.add(initial);
        visited.add(Arrays.deepToString(board) + "0");

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // we need to be sure that we are seeing all the pieces and after that we see that did we solve it!
            if (current.moves().size() == pieces.length && isSolved(current.board())) {
                printSolution(current.moves());
                return true;
            }

            int chosenPieceNumber = current.nextPieceIndex();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int[][] newBoard = cloneBoard(current.board());
                    if (placePiece(newBoard, pieces[chosenPieceNumber], row, col)) {
                        String boardKey = Arrays.deepToString(newBoard) + (chosenPieceNumber + 1) % pieces.length; // move to the next piece
                        if (!visited.contains(boardKey)) {
                            visited.add(boardKey);
                            List<Move> newMoves = new ArrayList<>(current.moves());
                            newMoves.add(new Move(chosenPieceNumber, row, col));
                            queue.add(new State(newBoard, newMoves, (chosenPieceNumber + 1) % pieces.length));
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isSolved(int[][] inputBoard) {
        for (int[] row : inputBoard) {
            for (int value : row) {
                if (value != 0) return false;
            }
        }
        return true;
    }

    private boolean placePiece(
            int[][] inputBoard,
            int[][] piece,
            int startX,
            int startY) {

        if (startX + piece.length > inputBoard.length
                || startY + piece[0].length > inputBoard[0].length) {
            return false;
        }

        boolean placed = false;
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    inputBoard[startX + i][startY + j] = (inputBoard[startX + i][startY + j] + 1) % depth;
                    placed = true;
                }
            }
        }
        return placed;
    }

    private int[][] cloneBoard(int[][] original) {
        return Arrays.stream(original).map(int[]::clone).toArray(int[][]::new);
    }

    private void printSolution(List<Move> moves) {
        moves.forEach(System.out::println);
    }
}
