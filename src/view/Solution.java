package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Solution<T> extends JFrame {
    private JTextArea solutionArea;

    public Solution(List<T> solutionSteps) {
        setTitle("Solution Found");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        solutionArea = new JTextArea(10, 30);
        solutionArea.setEditable(false);

        StringBuilder solutionText = new StringBuilder();
        for (T step : solutionSteps) {
            solutionText.append(step.toString()).append("\n");
        }
        solutionArea.setText(solutionText.toString());

        JScrollPane scrollPane = new JScrollPane(solutionArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}