import view.Menu;

import javax.swing.*;

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
