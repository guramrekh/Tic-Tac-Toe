import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel bodyPanel;
    private JButton[][] grid = new JButton[3][3];
    private  String playerX = "X";
    private  String playerO = "O";
    private String currentPlayer = playerX;
    private boolean gameOver = false;

    public MainFrame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(new ImageIcon("logo.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(400,450);

        headerLabel = new JLabel();
        headerLabel.setText(currentPlayer + "'s turn");
        headerLabel.setForeground(Color.black);
        headerLabel.setBackground(Color.white);
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        headerLabel.setPreferredSize(new Dimension(0, 50));
        frame.add(headerLabel, BorderLayout.NORTH);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(3,3,3,3));
        bodyPanel.setBackground(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton box = new JButton();
                grid[i][j] = box;
                bodyPanel.add(box);

                box.setBackground(Color.white);
                //box.setForeground(Color.red);
                box.setFont(new Font("Comfortaa", Font.PLAIN, 65));
                box.setFocusable(false);

                box.addActionListener(e -> {
                    if (gameOver) return;
                    JButton button = (JButton) e.getSource();
                    if (button.getText().isEmpty()){
                        button.setForeground(currentPlayer.equals(playerX) ? Color.red : Color.blue);
                        button.setText(currentPlayer);
                        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                        headerLabel.setText(currentPlayer + "'s turn");
                    }
                    if (checkWinner() == null && isDraw()) {
                        headerLabel.setText("Draw");
                        gameOver = true;
                    } else if (checkWinner() != null ) {
                        headerLabel.setText(checkWinner() + " won");
                        gameOver = true;
                    }
                });
            }
        }

        frame.add(bodyPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private String checkWinner() {
        // checking diagonals
        if (!grid[0][0].getText().isEmpty() && grid[0][0].getText().equals(grid[1][1].getText()) && grid[0][0].getText().equals(grid[2][2].getText()))
            return grid[0][0].getText();
        else if(!grid[0][2].getText().isEmpty() && grid[0][2].getText().equals(grid[1][1].getText()) && grid[0][2].getText().equals(grid[2][0].getText()))
            return grid[0][2].getText();

        // checking rows
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].getText().isEmpty() && grid[i][0].getText().equals(grid[i][1].getText()) && grid[i][0].getText().equals(grid[i][2].getText()))
                return grid[i][0].getText();
        }
        // checking columns
        for (int i = 0; i < 3; i++) {
            if (!grid[0][i].getText().isEmpty() && grid[0][i].getText().equals(grid[1][i].getText()) && grid[0][i].getText().equals(grid[2][i].getText()))
                return grid[0][i].getText();
        }
        return null;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(grid[i][j].getText().isEmpty()) return false;
            }
        }
        return true;
    }
}
