import javax.swing.*;

public class Launcher {
    /*
        Things to add:
        - scoreboard
        - player names
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().show());
    }
}
