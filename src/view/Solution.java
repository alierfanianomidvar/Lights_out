package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Solution extends JFrame {
    private JTextArea solutionArea;

    public Solution(List<String> solutionSteps) {
        setTitle("Solution Found");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        solutionArea = new JTextArea(10, 30);
        solutionArea.setEditable(false);

        StringBuilder solutionText = new StringBuilder();
        for (String step : solutionSteps) {
            solutionText.append(step).append("\n");
        }
        solutionArea.setText(solutionText.toString());

        JScrollPane scrollPane = new JScrollPane(solutionArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}