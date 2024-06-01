import javax.swing.*;

public class Launcher {
    /*
        Things to add:
        - scoreboard
        - select symbol
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayerRegistrationWindow().show());
    }
}
