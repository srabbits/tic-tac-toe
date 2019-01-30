package games.crusader.tictactoe;

public class GameEngine {
    public static final char EMPTY = ' ';
    public static final char X = 'X';
    public static final char O = 'O';
    private char[] board;
    private char player;
    private GameState gameState;

    public GameEngine() {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = EMPTY;
        }
        player = X;
        gameState = GameState.GAME_IN_PROGRESS;
    }

    public GameEngine(char player, char[] board) {
        this.player = player;
        this.board = board;
        gameState = GameState.GAME_IN_PROGRESS;
    }

    public String printBoard() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            builder.append(" " + board[i] + " ");
            if (i == 2 || i == 5 || i == 8) {
                builder.append("\n");

            } else {
                builder.append("|");
            }
            if (i == 2 || i == 5) {
                builder.append("-----------\n");
            }

        }
        return builder.toString();
    }

    public void select(int slot) {
        if (slot < 0 || slot > 8) {
            throw new TicTacToeException("Slot can only be 0 through 8");
        }

        if (board[slot] != EMPTY) {
            throw new TicTacToeException("Slot not Available ");
        }
        board[slot] = player;
        checkVictory(player);
        player = (player == X) ? O : X;

    }

    public GameState getState() {
        return gameState;
    }

    public GameState checkVictory(char player) {
        if (isRow(0, 1, 2) ||
                isRow(3, 4, 5) ||
                isRow(6, 7, 8) ||
                isRow(0, 3, 6) ||
                isRow(1, 4, 7) ||
                isRow(2, 5, 8) ||
                isRow(0, 4, 8) ||
                isRow(2, 4, 6)) {
            if (player == X) {
                gameState = GameState.X_WINS;
            } else {
                gameState = GameState.O_WINS;
            }
        } else {
            if (isFull()) {
                gameState = GameState.DRAW;
            }
        }
        return gameState;
    }

    private boolean isFull() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private boolean isRow(int i, int i2, int i3) {
        return board[i] != EMPTY && board[i] == board[i2] && board[i2] == board[i3];
    }

    public char getPlayer() {
        return player;
    }
}
