import lights_out.LightsOut;
import lights_out.LightsOutBfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {

            boolean bfsFlag = false;
            File inputFile = new File("src\\samples\\04.txt");
            Scanner scanner = new Scanner(inputFile);

            // Read the depth of the puzzle
            int depth = scanner.nextInt();
            scanner.nextLine(); // Move to the next line

            // Read the initial board state and convert to 2D int array
            String[] rows = scanner.nextLine().split(",");
            int[][] board = new int[rows.length][rows[0].length()];
            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < rows[i].length(); j++) {
                    board[i][j] = rows[i].charAt(j) - '0';
                }
            }

            // Read the individual pieces and convert to 3D int array
            String[] pieceStrings = scanner.nextLine().trim().split("\\s+");
            int[][][] pieces = new int[pieceStrings.length][][];
            for (int i = 0; i < pieceStrings.length; i++) {
                String[] pieceRows = pieceStrings[i].split(",");
                pieces[i] = new int[pieceRows.length][];
                for (int j = 0; j < pieceRows.length; j++) {
                    pieces[i][j] = new int[pieceRows[j].length()];
                    for (int k = 0; k < pieceRows[j].length(); k++) {
                        char c = pieceRows[j].charAt(k);
                        pieces[i][j][k] = (c == 'X') ? 1 : 0;
                    }
                }
            }

            if (!bfsFlag) {
                LightsOut solver = new LightsOut(depth, board, pieces);

                boolean solutionFound = solver.solve(0);
                if (solutionFound) {
                    System.out.println("Solution found!");
                } else {
                    System.out.println("No solution exists.");
                }
            } else {
                LightsOutBfs bfsSolver = new LightsOutBfs(depth, board, pieces);

                boolean bfsSolutionFound = bfsSolver.solve();

                if (bfsSolutionFound) {
                    System.out.println("BFS Solution found!");
                } else {
                    System.out.println("BFS No solution exists.");
                }
            }
            scanner.close();

            // The depth, board, and pieces are now populated with the data from the file.
            // Add code here to process them and solve the puzzle.

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to read the file.");
            e.printStackTrace();
        }
    }
}
