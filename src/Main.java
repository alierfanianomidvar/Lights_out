import lights_out.LightsOut;
import lights_out.LightsOutBfs;
import lights_out.LightsOutIDAStar;
import view.MatrixDisplay;
import view.Menu;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Menu().setVisible(true);
                }
            });


        } catch (Exception e) {
            System.out.println("An error occurred while trying to read the file.");
            e.printStackTrace();
        }
    }
}
