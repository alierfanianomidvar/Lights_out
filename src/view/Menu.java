package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import lights_out.LightsOut;
import lights_out.LightsOutBfs;
import lights_out.LightsOutIDAStar;
import view.MatrixDisplay;

public class Menu extends JFrame {
    private JButton openButton;
    private JFileChooser fileChooser;
    private JLabel imageLabel;
    private JLabel textLabel;

    private JComboBox<String> optionsComboBox;

    public Menu() {
        super("Light out");
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 410);
        setLocationRelativeTo(null);
        setLayout(null);

        textLabel = new JLabel("! Light Out !");
        textLabel.setFont(new Font("Arial", Font.BOLD, 16));
        textLabel.setBounds(2, 30, 400, 30);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(textLabel);


        ImageIcon image = new ImageIcon("src/util/400px-LightsOutIllustration.svg.png");
        imageLabel = new JLabel(image);
        imageLabel.setBounds(2, 50, 400, 300);
        add(imageLabel);


        String[] options = {"Back Track", "BFS", "IDA-STAR"};
        optionsComboBox = new JComboBox<>(options);
        optionsComboBox.setBounds(2, 300, 400, 30);
        optionsComboBox.setSelectedIndex(0);
        add(optionsComboBox);

        openButton = new JButton("Choose And Start");
        openButton.setBounds(2, 335, 400, 30);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String selectedOption = (String) optionsComboBox.getSelectedItem();
                    processFile(selectedFile, selectedOption);
                    dispose();
                }
            }
        });
        add(openButton);

        // Configure the file chooser
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    }

    private void processFile(File file, String option) {
        try {
            Scanner scanner = new Scanner(file);
            boolean bfsFlag = false;

            int depth = scanner.nextInt();
            scanner.nextLine();

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

            new MatrixDisplay(board, pieces);

            switch (option) {
                case "IDA-STAR":
                    LightsOutIDAStar idaStarSolver = new LightsOutIDAStar(depth, board, pieces);

                    boolean idaStarSolutionFound = idaStarSolver.solve();

                    if (idaStarSolutionFound) {
                        System.out.println("IDA* Solution found!");
                    } else {
                        System.out.println("IDA* No solution exists.");
                    }
                    break;
                case "BFS":
                    LightsOutBfs bfsSolver = new LightsOutBfs(depth, board, pieces);

                    boolean bfsSolutionFound = bfsSolver.solve();

                    if (bfsSolutionFound) {
                        System.out.println("BFS Solution found!");
                    } else {
                        System.out.println("BFS No solution exists.");
                    }
                    break;
                case "Back Track":
                default:
                    LightsOut solver = new LightsOut(depth, board, pieces);

                    boolean solutionFound = solver.solve(0);
                    if (solutionFound) {
                        System.out.println("Solution found!");
                    } else {
                        System.out.println("No solution exists.");
                    }
                    break;
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
