package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MatrixDisplay extends JFrame {
    private int[][] matrix;

    public MatrixDisplay(int[][] matrix) {
        this.matrix = matrix;

        setTitle("Lights Out");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                label.setPreferredSize(new Dimension(60, 60));
                matrixPanel.add(label);
            }
        }

        matrixPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().add(matrixPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}