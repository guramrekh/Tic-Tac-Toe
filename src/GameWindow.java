import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    
    // Colors
    static final Color OFF_WHITE = new Color(251, 241, 249);
    static final Color LIGHT_PINK = new Color(248, 204, 249);
    static final Color DARK_PINK = new Color(255, 89, 143);
    static final Color VIOLET = new Color(126, 75, 139);
    static final Color NAVY_BLUE = new Color(1, 21, 62);
    static final Color ORANGE = new Color(255, 102, 0);
    static final Color BABY_BLUE = new Color(173, 216, 230);
    static final Color GRAY = new Color(128, 128, 128);
    static final Color PURPLEISH_GRAY = new Color(174, 157, 169);
    static final Color LIGHT_GRAY = new Color(170, 170, 170);

    public GameWindow(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.currentPlayerName = player1Name;

        setupMainFrame();
        setupHeaderLabel();
        setupBodyPanel();
        setupBottomPanel();

        frame.add(headerLabel, BorderLayout.NORTH);
        frame.add(bodyPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupMainFrame() {
        frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(new ImageIcon("logo2.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void setupHeaderLabel() {
        headerLabel = new JLabel();
        headerLabel.setText(currentPlayerName + "'s turn");
        headerLabel.setForeground(VIOLET);
        headerLabel.setBackground(LIGHT_PINK);
        headerLabel.setOpaque(true);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        headerLabel.setPreferredSize(new Dimension(0, 70));
    }

    private void setupBodyPanel() {
        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(3,3,3,3));
        bodyPanel.setBackground(LIGHT_PINK);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                setupGridButtons(i, j);
            }
        }
    }

    private void setupGridButtons(int i, int j) {
        JButton box = new JButton();
        grid[i][j] = box;
        bodyPanel.add(box);

        box.setBackground(OFF_WHITE);
        box.setForeground(VIOLET);
        box.setFont(new Font("Comic Sans MS", Font.PLAIN, 72));
        box.setFocusable(false);

        box.addActionListener(e -> handleGridButtonClick(box));
    }

    private void handleGridButtonClick(JButton button) {
        if (gameOver) return;
        if (button.getText().isEmpty()){
            button.setText(currentPlayerSymbol);
            currentPlayerSymbol = currentPlayerSymbol.equals(PLAYER_1_SYMBOL) ? PLAYER_2_SYMBOL : PLAYER_1_SYMBOL;
            currentPlayerName = currentPlayerSymbol.equals(PLAYER_1_SYMBOL) ? this.player1Name : this.player2Name;
            headerLabel.setText(currentPlayerName + "'s turn");
        }
        String winner = checkWinner();
        if (winner == null && isGridFull()) {
            headerLabel.setBackground(DARK_PINK);
            headerLabel.setForeground(LIGHT_PINK);
            headerLabel.setText("Tie!");
            gameOver = true;
        } else if (winner != null) {
            headerLabel.setBackground(VIOLET);
            headerLabel.setForeground(LIGHT_PINK);
            headerLabel.setText(winner + " won!");
            updateScoreLabel(winner.equals(this.player1Name) ? this.player1Name : this.player2Name);
            gameOver = true;
        }
    }

    private String checkWinner() {
        // Check diagonals
        if (isWinner(grid[0][0], grid[1][1], grid[2][2])) return getPlayerName(grid[0][0]);
        if (isWinner(grid[0][2], grid[1][1], grid[2][0])) return getPlayerName(grid[0][2]);

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (isWinner(grid[i][0], grid[i][1], grid[i][2])) return getPlayerName(grid[i][0]);
            if (isWinner(grid[0][i], grid[1][i], grid[2][i])) return getPlayerName(grid[0][i]);
        }

        return null;
    }

    private boolean isWinner(JButton b1, JButton b2, JButton b3) {
        if (b1.getText().isEmpty()) return false;
        if (b1.getText().equals(b2.getText()) && b1.getText().equals(b3.getText())) {
            visualizeWinner(b1, b2, b3);
            return true;
        }
        return false;
    }

    private String getPlayerName(JButton button) {
        return button.getText().equals(PLAYER_1_SYMBOL) ? player1Name : player2Name;
    }

    private void visualizeWinner(JButton... buttons) {
        for (JButton button : buttons) {
            button.setForeground(DARK_PINK);
        }
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

    private void updateScoreLabel(String winner) {
        String colorToHex = String.format("#%02x%02x%02x", DARK_PINK.getRed(), DARK_PINK.getGreen(), DARK_PINK.getBlue());
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

    private void setupBottomPanel() {
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(0, 80));

        setupScoreboardPanel();
        setupRestartButton();

        bottomPanel.add(restartButton, BorderLayout.SOUTH);
        bottomPanel.add(scoreboardPanel, BorderLayout.CENTER);
    }

    private void setupScoreboardPanel() {
        scoreboardPanel = new JPanel();
        scoreboardPanel.setBackground(LIGHT_PINK);

        scoreLabel = new JLabel();
        scoreLabel.setText(this.player1Name + " " + player1Score + ":" + player2Score + " " + this.player2Name);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        scoreLabel.setForeground(VIOLET);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);

        scoreboardPanel.add(scoreLabel);
    }

    private void setupRestartButton() {
        restartButton = new JButton();
        restartButton.setText("Restart");
        restartButton.setPreferredSize(new Dimension(100, 30));
        restartButton.setFocusable(false);
        restartButton.setBackground(VIOLET);
        restartButton.setForeground(LIGHT_PINK);
        restartButton.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        restartButton.addActionListener(e -> resetGame());
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setText("");
                grid[i][j].setForeground(VIOLET);
            }
        }
        gameOver = false;
        headerLabel.setBackground(LIGHT_PINK);
        headerLabel.setText(currentPlayerName + "'s turn");
        headerLabel.setForeground(VIOLET);
        updateScoreLabelColors();
    }

    private void updateScoreLabelColors() {
        String colorToHex = String.format("#%02x%02x%02x", VIOLET.getRed(), VIOLET.getGreen(), VIOLET.getBlue());

        String player1ScoreStr = "<span style='color:" + colorToHex + ";'>" + player1Score + "</span>";
        String player2ScoreStr = "<span style='color:" + colorToHex + ";'>" + player2Score + "</span>";
        String updatedScoreLabel = "<html>" + player1Name + " " + player1ScoreStr + ":" + player2ScoreStr + " " + player2Name + "</html>";

        scoreLabel.setText(updatedScoreLabel);
    }

    public void show() {
        frame.setVisible(true);
    }
}
