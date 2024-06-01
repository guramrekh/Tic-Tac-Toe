import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel bodyPanel;
    private final JButton[][] grid = new JButton[3][3];
    private JButton restartButton;
    private String player1Name;
    private String player2Name;
    private String currentPlayerName;
    private final String PLAYER_1_SYMBOL = "X";
    private final String PLAYER_2_SYMBOL = "O";
    private String currentPlayerSymbol = PLAYER_1_SYMBOL;
    private boolean gameOver = false;
    static Color navyBlue = new Color(1,21,62);
    static Color orange = new Color(255,102,0);
    static Color babyBlue = new Color(173, 216, 230);
    static Color gray = new Color(128, 128, 128);

    public GameWindow(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.currentPlayerName = player1Name;

        frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(new ImageIcon("logo2.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        headerLabel = new JLabel();
        headerLabel.setText(currentPlayerName + "'s turn");
        headerLabel.setForeground(babyBlue);
        headerLabel.setBackground(gray);
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        headerLabel.setPreferredSize(new Dimension(0, 70));
        frame.add(headerLabel, BorderLayout.NORTH);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(3,3,3,3));
        bodyPanel.setBackground(gray);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton box = new JButton();
                grid[i][j] = box;
                bodyPanel.add(box);

                box.setBackground(babyBlue);
                box.setForeground(navyBlue);
                box.setFont(new Font("Comic Sans MS", Font.PLAIN, 72));
                box.setFocusable(false);

                box.addActionListener(e -> {
                    if (gameOver) return;
                    JButton button = (JButton) e.getSource();
                    if (button.getText().isEmpty()){
                        button.setText(currentPlayerSymbol);
                        currentPlayerSymbol = currentPlayerSymbol.equals(PLAYER_1_SYMBOL) ? PLAYER_2_SYMBOL : PLAYER_1_SYMBOL;
                        currentPlayerName = currentPlayerSymbol.equals(PLAYER_1_SYMBOL) ? player1Name : player2Name;
                        headerLabel.setText(currentPlayerName + "'s turn");
                    }
                    if (isDraw()) {
                        headerLabel.setBackground(orange);
                        headerLabel.setForeground(navyBlue);
                        headerLabel.setText("Tie");
                        gameOver = true;
                    } else if (checkWinner() != null) {
                        headerLabel.setBackground(orange);
                        headerLabel.setForeground(navyBlue);
                        headerLabel.setText(checkWinner() + " won!");
                        gameOver = true;
                    }
                });
            }
        }
        restartButton = new JButton();
        restartButton.setText("Restart");
        restartButton.setPreferredSize(new Dimension(0, 30));
        restartButton.setFocusable(false);
        restartButton.setBackground(orange);
        restartButton.setForeground(navyBlue);
        restartButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        restartButton.addActionListener(e -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid[i][j].setText("");
                    grid[i][j].setForeground(navyBlue);
                }
            }
            gameOver = false;
            headerLabel.setBackground(gray);
            headerLabel.setText(currentPlayerName + "'s turn");
            headerLabel.setForeground(babyBlue);
        });


        frame.add(restartButton, BorderLayout.SOUTH);
        frame.add(bodyPanel, BorderLayout.CENTER);

    }
    public void show() {
        frame.setVisible(true);
    }

    private String checkWinner() {
        // checking diagonals
        if (!grid[0][0].getText().isEmpty() && grid[0][0].getText().equals(grid[1][1].getText())
                && grid[0][0].getText().equals(grid[2][2].getText())){
            for (int i = 0; i < 3; i++) {
                visualizeWinner(grid[i][i]);
            }
            return grid[0][0].getText().equals("X") ? player1Name : player2Name;
        }
        else if(!grid[0][2].getText().isEmpty() && grid[0][2].getText().equals(grid[1][1].getText())
                && grid[0][2].getText().equals(grid[2][0].getText())){
            for (int i = 0; i < 3; i++) {
                visualizeWinner(grid[i][2-i]);
            }
            return grid[0][2].getText().equals("X") ? player1Name : player2Name;
        }

        // checking rows
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].getText().isEmpty() && grid[i][0].getText().equals(grid[i][1].getText())
                    && grid[i][0].getText().equals(grid[i][2].getText())){
                for (int j = 0; j < 3; j++) {
                    visualizeWinner(grid[i][j]);
                }
                return grid[i][0].getText().equals("X") ? player1Name : player2Name;
            }
        }

        // checking columns
        for (int i = 0; i < 3; i++) {
            if (!grid[0][i].getText().isEmpty() && grid[0][i].getText().equals(grid[1][i].getText())
                    && grid[0][i].getText().equals(grid[2][i].getText())){
                for (int j = 0; j < 3; j++) {
                    visualizeWinner(grid[j][i]);
                }
                return grid[0][i].getText().equals("X") ? player1Name : player2Name;
            }
        }
        return null;
    }

    private boolean isDraw() {
        if(checkWinner() != null)
            return false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getText().isEmpty())
                    return false;
            }
        }
        return true;
    }

    private void visualizeWinner(JButton button) {
        button.setForeground(orange);
//        headerLabel.setBackground(orange);
    }
}
