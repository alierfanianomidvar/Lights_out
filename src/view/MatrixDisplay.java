package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MatrixDisplay extends JFrame {

    public MatrixDisplay(
            int[][] matrix,
            int[][][] piecesMatrix) {

        setTitle("Lights Out");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main board - >
        int mainCellSize = 60;
        Dimension mainMatrixSize = calculateDimension(matrix, mainCellSize); //daynamic size base on the input

        JPanel mainBoard = createMatrixPanel(matrix, mainCellSize, mainCellSize); // creating the main board. - > main is the initial matrix.
        getContentPane().add(mainBoard, BorderLayout.WEST); // the main board the left side !

        // Pieces - >
        JPanel piecesPanel = new JPanel(new GridLayout(0, 1));  // 0 means any number of rows
        int piecesCellSize = 20;
        Dimension piecesMatrixSize = new Dimension(0, 0);

        for (int[][] pieceMatrix : piecesMatrix) {
            JPanel panel = createMatrixPanel(pieceMatrix, piecesCellSize, piecesCellSize);
            piecesPanel.add(panel);
            Dimension size = calculateDimension(pieceMatrix, piecesCellSize);
            piecesMatrixSize.width = Math.max(piecesMatrixSize.width, size.width);
            piecesMatrixSize.height += size.height;
        }

        // scroll down - >
        JScrollPane scrollPane = new JScrollPane(piecesPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(piecesMatrixSize);
        getContentPane().add(scrollPane, BorderLayout.EAST);

        // find the frame size - >
        pack();
        int width = mainMatrixSize.width + piecesMatrixSize.width + 50;
        int height = Math.max(mainMatrixSize.height, piecesMatrixSize.height) + 50;
        setPreferredSize(new Dimension(width, height));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Dimension calculateDimension(
            int[][] matrix,
            int cellSize) {
        int n = matrix.length;
        int m = matrix[0].length;
        return new Dimension(m * cellSize + 40, n * cellSize + 40); // 40 is a margin !!
    }

    private JPanel createMatrixPanel(
            int[][] matrix,
            int cellWidth,
            int cellHeight) {

        int n = matrix.length;
        int m = matrix[0].length;
        JPanel matrixPanel = new JPanel(new GridLayout(n, m));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                JLabel label = new JLabel(String.valueOf(matrix[i][j]));
                label.setBorder(border);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setPreferredSize(new Dimension(cellWidth, cellHeight));
                matrixPanel.add(label);
            }
        }

        matrixPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return matrixPanel;
    }
}
