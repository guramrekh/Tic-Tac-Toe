import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame frame;
    private JLabel headerLabel;
    private JPanel bodyPanel;
    private final JButton[][] grid = new JButton[3][3];
    private JPanel bottomPanel;
    private JPanel scoreboardPanel;
    private JLabel scoreLabel;
    private int player1Score = 0;
    private int player2Score = 0;
    private JButton restartButton;
    private String player1Name;
    private String player2Name;
    private String currentPlayerName;
    private final String PLAYER_1_SYMBOL = "X";
    private final String PLAYER_2_SYMBOL = "O";
    private String currentPlayerSymbol = PLAYER_1_SYMBOL;
    private boolean gameOver = false;
    static Color offWhite = new Color(251,241,249);
    static Color lightPink = new Color(248,204,249);
    static Color darkPink = new Color(255,89,143);
    static Color violet = new Color(126, 75, 139);
    static Color navyBlue = new Color(1,21,62);
    static Color orange = new Color(255,102,0);
    static Color babyBlue = new Color(173, 216, 230);
    static Color gray = new Color(128, 128, 128);
    static Color purpleishGray = new Color(174, 157, 169);
    static Color lightGray = new Color(170, 170, 170);

    public GameWindow(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.currentPlayerName = player1Name;

        frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(new ImageIcon("logo2.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        headerLabel = new JLabel();
        headerLabel.setText(currentPlayerName + "'s turn");
        headerLabel.setForeground(violet);
        headerLabel.setBackground(lightPink);
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        headerLabel.setPreferredSize(new Dimension(0, 70));
        frame.add(headerLabel, BorderLayout.NORTH);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(3,3,3,3));
        bodyPanel.setBackground(lightPink);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton box = new JButton();
                grid[i][j] = box;
                bodyPanel.add(box);

                box.setBackground(offWhite);
                box.setForeground(violet);
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
                    String winner = checkWinner();
                    if (winner == null && isGridFull()) {
                        headerLabel.setBackground(darkPink);
                        headerLabel.setForeground(lightPink);
                        headerLabel.setText("Tie!");
                        gameOver = true;
                    } else if (winner != null) {
                        headerLabel.setBackground(violet);
                        headerLabel.setForeground(lightPink);
                        headerLabel.setText(winner + " won!");
                        updateScoreLabel(winner.equals(player1Name) ? player1Name : player2Name);
                        gameOver = true;
                    }
                });
            }
        }
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(0, 80));

        scoreboardPanel = new JPanel();
        scoreboardPanel.setBackground(lightPink);
        scoreLabel = new JLabel();
        scoreLabel.setText(player1Name + " " + player1Score + ":" + player2Score + " " + player2Name);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        scoreLabel.setForeground(violet);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scoreboardPanel.add(scoreLabel);

        restartButton = new JButton();
        restartButton.setText("Restart");
        restartButton.setPreferredSize(new Dimension(100, 30));
        restartButton.setFocusable(false);
        restartButton.setBackground(violet);
        restartButton.setForeground(lightPink);
        restartButton.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        restartButton.addActionListener(e -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid[i][j].setText("");
                    grid[i][j].setForeground(violet);
                }
            }
            gameOver = false;
            headerLabel.setBackground(lightPink);
            headerLabel.setText(currentPlayerName + "'s turn");
            headerLabel.setForeground(violet);
            updateScoreLabelColors();
        });
        bottomPanel.add(restartButton, BorderLayout.SOUTH);
        bottomPanel.add(scoreboardPanel, BorderLayout.CENTER);

        frame.add(bodyPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
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

    private boolean isGridFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].getText().isEmpty())
                    return false;
            }
        }
        return true;
    }

    private void visualizeWinner(JButton button) {
        button.setForeground(darkPink);
    }

    private void updateScoreLabel(String winner) {
        String colorToHex = String.format("#%02x%02x%02x", darkPink.getRed(), darkPink.getGreen(), darkPink.getBlue());
        String updatedScoreLabel = "";
        if (winner.equals(player1Name)){
            player1Score++;
            String player1ScoreStr = "<span style='color:" + colorToHex + ";'>" + player1Score + "</span>";
            String player2ScoreStr = Integer.toString(player2Score);
            updatedScoreLabel = "<html>" + player1Name + " " + player1ScoreStr + ":" + player2ScoreStr + " " + player2Name + "</html>";
        }
        else {
            player2Score++;
            String player1ScoreStr = Integer.toString(player1Score);
            String player2ScoreStr = "<span style='color:" + colorToHex + ";'>" + player2Score + "</span>";
            updatedScoreLabel = "<html>" + player1Name + " " + player1ScoreStr + ":" + player2ScoreStr + " " + player2Name + "</html>";
        }

        scoreLabel.setText(updatedScoreLabel);
    }

    private void updateScoreLabelColors() {
        String colorToHex = String.format("#%02x%02x%02x", violet.getRed(), violet.getGreen(), violet.getBlue());

        String player1ScoreStr = "<span style='color:" + colorToHex + ";'>" + player1Score + "</span>";
        String player2ScoreStr = "<span style='color:" + colorToHex + ";'>" + player2Score + "</span>";
        String updatedScoreLabel = "<html>" + player1Name + " " + player1ScoreStr + ":" + player2ScoreStr + " " + player2Name + "</html>";

        scoreLabel.setText(updatedScoreLabel);
    }
}
