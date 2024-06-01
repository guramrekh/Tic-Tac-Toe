import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel bodyPanel;
    private final JButton[][] grid = new JButton[3][3];
    private JButton restartButton;
    private final String playerX = "X";
    private final String playerO = "O";
    private String currentPlayer = playerX;
    private boolean gameOver = false;
    Color navyBlue = new Color(1,21,62);
    Color orange = new Color(255,102,0);
    Color babyBlue = new Color(173, 216, 230);

    public MainFrame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(new ImageIcon("logo.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        headerLabel = new JLabel();
        headerLabel.setText(currentPlayer + "'s Turn");
        headerLabel.setForeground(babyBlue);
        headerLabel.setBackground(new Color(105, 105, 105));
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        headerLabel.setPreferredSize(new Dimension(0, 70));
        frame.add(headerLabel, BorderLayout.NORTH);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(3,3,3,3));
        bodyPanel.setBackground(new Color(105, 105, 105));
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
//                        button.setForeground(currentPlayer.equals(playerX) ? Color.red : Color.blue);
                        button.setText(currentPlayer);
                        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                        headerLabel.setText(currentPlayer + "'s turn");
                    }
                    if (checkWinner() == null && isDraw()) {
                        headerLabel.setText("Tie");
                        gameOver = true;
                    } else if (checkWinner() != null ) {
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
            headerLabel.setBackground(new Color(105, 105, 105));
            headerLabel.setText(currentPlayer + "'s Turn");
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
            return grid[0][0].getText();
        }
        else if(!grid[0][2].getText().isEmpty() && grid[0][2].getText().equals(grid[1][1].getText())
                && grid[0][2].getText().equals(grid[2][0].getText())){
            for (int i = 0; i < 3; i++) {
                visualizeWinner(grid[i][2-i]);
            }
            return grid[0][2].getText();
        }

        // checking rows
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].getText().isEmpty() && grid[i][0].getText().equals(grid[i][1].getText())
                    && grid[i][0].getText().equals(grid[i][2].getText())){
                for (int j = 0; j < 3; j++) {
                    visualizeWinner(grid[i][j]);
                }
                return grid[i][0].getText();
            }
        }

        // checking columns
        for (int i = 0; i < 3; i++) {
            if (!grid[0][i].getText().isEmpty() && grid[0][i].getText().equals(grid[1][i].getText())
                    && grid[0][i].getText().equals(grid[2][i].getText())){
                for (int j = 0; j < 3; j++) {
                    visualizeWinner(grid[j][i]);
                }
                return grid[0][i].getText();
            }
        }
        return null;
    }

    private boolean isDraw() {
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
        headerLabel.setBackground(orange);
    }
}
