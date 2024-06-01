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
        frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(new ImageIcon("logo.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
        frame.getContentPane().setBackground(GameWindow.gray);

        player1Label = new JLabel();
        player1Label.setText("Player 1: ");
        player1Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        player1Label.setForeground(GameWindow.babyBlue);
        player1TextField = new JTextField(10);
        player1TextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));

        player2Label = new JLabel();
        player2Label.setText("Player 2: ");
        player2Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        player2Label.setForeground(GameWindow.babyBlue);
        player2TextField = new JTextField(10);
        player2TextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));

        continueButton = new JButton("Continue");
        continueButton.setPreferredSize(new Dimension(115,40));
        continueButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        continueButton.setFocusable(false);
        continueButton.setBackground(GameWindow.babyBlue);
        continueButton.setForeground(GameWindow.navyBlue);
        continueButton.addActionListener(e -> {
            GameWindow gameWindow = new GameWindow(player1TextField.getText(), player2TextField.getText());
//            gameWindow.setPlayer1name(player1TextField.getText());
//            gameWindow.setPlayer2name(player2TextField.getText());

//            System.out.println(player1TextField.getText());
//            System.out.println(player2TextField.getText());

            frame.setVisible(false);
            gameWindow.show();
        });

        frame.add(player1Label);
        frame.add(player1TextField);
        frame.add(player2Label);
        frame.add(player2TextField);
        frame.add(continueButton);
    }

    public void show() {
        frame.setVisible(true);
    }

}
