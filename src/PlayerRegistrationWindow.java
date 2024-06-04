import javax.swing.*;
import java.awt.*;

public class PlayerRegistrationWindow {
    private JFrame frame;
    private JLabel player1Label;
    private JLabel player2Label;
    private JTextField player1TextField;
    private JTextField player2TextField;
    private JButton continueButton;


    public PlayerRegistrationWindow() {
        setupMainFrame();
        setupPlayerLabels();
        setupPlayerTextFields();
        setupContinueButton();

        frame.add(player1Label);
        frame.add(player1TextField);
        frame.add(player2Label);
        frame.add(player2TextField);
        frame.add(continueButton);
    }

    private void setupMainFrame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(new ImageIcon("logo2.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
        frame.getContentPane().setBackground(GameWindow.LIGHT_PINK);
    }

    private void setupPlayerLabels() {
        player1Label = new JLabel();
        player1Label.setText("Player X: ");
        player1Label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        player1Label.setForeground(GameWindow.VIOLET);
        player2Label = new JLabel();
        player2Label.setText("Player O: ");
        player2Label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        player2Label.setForeground(GameWindow.VIOLET);
    }

    private void setupPlayerTextFields() {
        player1TextField = new JTextField(10);
        player1TextField.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        player1TextField.setBackground(GameWindow.OFF_WHITE);
        player1TextField.setBackground(GameWindow.OFF_WHITE);
        player1TextField.setForeground(GameWindow.DARK_PINK);

        player2TextField = new JTextField(10);
        player2TextField.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        player2TextField.setBackground(GameWindow.OFF_WHITE);
        player2TextField.setForeground(GameWindow.DARK_PINK);
    }

    private void setupContinueButton() {
        continueButton = new JButton("Continue");
        continueButton.setPreferredSize(new Dimension(115,40));
        continueButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        continueButton.setFocusable(false);
        continueButton.setBackground(GameWindow.OFF_WHITE);
        continueButton.setForeground(GameWindow.VIOLET);
        continueButton.addActionListener(e -> {
            GameWindow gameWindow = new GameWindow(player1TextField.getText(), player2TextField.getText());
            frame.setVisible(false);
            gameWindow.show();
        });
    }

    public void show() {
        frame.setVisible(true);
    }

}
