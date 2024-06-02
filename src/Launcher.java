import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayerRegistrationWindow().show());
//        SwingUtilities.invokeLater(() -> new GameWindow("jon", "dom").show());
    }
}
