import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private char currentPlayer = 'X';
    private JButton[][] buttons = new JButton[3][3];

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 3));

        // Create the game buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 30));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton);
    }

    // Handle button clicks
    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board[row][col] == ' ' && !checkWinner() && !checkDraw()) {
                board[row][col] = currentPlayer;
                buttons[row][col].setText(String.valueOf(currentPlayer));
                if (checkGameState()) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    // Check game state (win or draw)
    private boolean checkGameState() {
        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            return false;
        }
        if (checkDraw()) {
            JOptionPane.showMessageDialog(this, "The game is a draw!");
            return false;
        }
        return true;
    }

    // Check for a winning condition
    private boolean checkWinner() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    // Check if the game is a draw
    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Reset the game
    private void resetGame() {
        board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
