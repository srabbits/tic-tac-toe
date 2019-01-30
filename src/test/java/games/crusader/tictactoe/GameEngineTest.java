package games.crusader.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameEngineTest {
    private static final String EMPTY_BOARD = "" +
            "   |   |   \n" +
            "-----------\n" +
            "   |   |   \n" +
            "-----------\n" +
            "   |   |   \n";

    private static final String X_BOARD = "" +
            " X |   |   \n" +
            "-----------\n" +
            "   |   |   \n" +
            "-----------\n" +
            "   |   |   \n";

    private static final String XO_BOARD = "" +
            " X |   |   \n" +
            "-----------\n" +
            "   | O |   \n" +
            "-----------\n" +
            "   |   |   \n";

    private GameEngine game = new GameEngine();


    @Test
    public void defaultValueForGameStateIs_GAME_IN_PROGRESS() {
        assertEquals(GameState.GAME_IN_PROGRESS, game.getState());


    }

    @Test
    public void printBoard_emptyBoard_printsEmptyGrid() {
        assertEquals(EMPTY_BOARD, game.printBoard());
    }

    @Test
    public void printBoard_OneXInCorner_printsGrid() {
        game.select(0);
        assertEquals(X_BOARD, game.printBoard());
    }

    @Test
    public void printBoard_OneXInCornerOInMiddle_printsGrid() {
        game.select(0);
        game.select(4);
        assertEquals(XO_BOARD, game.printBoard());
    }

    @Test
    public void checkVictory_TopRowXwins() {
        game.select(0);
        game.select(4);
        game.select(1);
        game.select(3);
        game.select(2);
        System.out.println(game.printBoard());
        assertEquals(GameState.X_WINS, game.getState());
    }

    @Test
    public void checkVictory_TopRowOwins() {
        game.select(8);
        game.select(0);
        game.select(4);
        game.select(1);
        game.select(3);
        game.select(2);
        System.out.println(game.printBoard());
        assertEquals(GameState.O_WINS, game.getState());

    }

    @Test(expected = TicTacToeException.class)
    public void select_TwiceOnSameSpotThrowsError() {
        game.select(0);
        game.select(0);

    }

    @Test(expected = TicTacToeException.class)
    public void select_SlotLessThenZeroThrowsError() {
        game.select(-1);
    }

    @Test(expected = TicTacToeException.class)
    public void select_SlotMoreThenNineThrowsError() {
        game.select(9);
    }

    @Test
    public void checkVictory_MidRowXwins() {
        game = new GameEngine('X', new char[]{' ', ' ', ' ', 'X', 'X', 'X', ' ', ' ', ' '});
        assertEquals(GameState.X_WINS, game.checkVictory('X'));
    }

    @Test
    public void checkVictory_MidRowOwins() {
        game = new GameEngine('O', new char[]{'X', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' '});
        assertEquals(GameState.O_WINS, game.checkVictory('O'));
    }

    @Test
    public void checkVictory_BottomRowXwins() {
        game = new GameEngine('X', new char[]{' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X'});
        assertEquals(GameState.X_WINS, game.checkVictory('X'));
    }

    @Test
    public void checkVictory_BottomRowOwins() {
        game = new GameEngine('O', new char[]{' ', ' ', ' ', ' ', ' ', ' ', 'O', 'O', 'O'});
        assertEquals(GameState.O_WINS, game.checkVictory('O'));
    }

    @Test
    public void checkVictory_LeftVerticalXWins() {
        game = new GameEngine('X', new char[]{'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' '});
        assertEquals(GameState.X_WINS, game.checkVictory('X'));
    }

    @Test
    public void checkVictory_LeftVerticalOWins() {
        char p = 'O';

        game = new GameEngine(p, new char[]{p, ' ', ' ', p, ' ', ' ', p, ' ', ' '});
        assertEquals(GameState.O_WINS, game.checkVictory(p));

    }

    @Test
    public void checkVictory_MiddleVerticalXWins() {
        char p = 'X';
        game = new GameEngine(p, new char[]{' ', p, ' ', ' ', p, ' ', ' ', p, ' '});
        assertEquals(GameState.X_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_MiddleVerticalOWins() {
        char p = 'O';
        game = new GameEngine(p, new char[]{' ', p, ' ', ' ', p, ' ', ' ', p, ' '});
        assertEquals(GameState.O_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_RightVerticalXWins() {
        char p = 'X';
        game = new GameEngine(p, new char[]{' ', ' ', p, ' ', ' ', p, ' ', ' ', p});
        assertEquals(GameState.X_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_RightVerticalOWins() {
        char p = 'O';
        game = new GameEngine(p, new char[]{' ', ' ', p, ' ', ' ', p, ' ', ' ', p});
        assertEquals(GameState.O_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_LeftDiagonalXWins() {
        char p = 'X';
        game = new GameEngine(p, new char[]{p, ' ', ' ', ' ', p, ' ', ' ', ' ', p});
        assertEquals(GameState.X_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_LeftDiagonalOWins() {
        char p = 'O';
        game = new GameEngine(p, new char[]{p, ' ', ' ', ' ', p, ' ', ' ', ' ', p});
        assertEquals(GameState.O_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_RightDiagonalXWins() {
        char p = 'X';
        game = new GameEngine(p, new char[]{' ', ' ', p, ' ', p, ' ', p, ' ', ' '});
        assertEquals(GameState.X_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_RightDiagonalOWins() {
        char p = 'O';
        game = new GameEngine(p, new char[]{' ', ' ', p, ' ', p, ' ', p, ' ', ' '});
        assertEquals(GameState.O_WINS, game.checkVictory(p));
    }

    @Test
    public void checkVictory_FullBoardReturnsDraw() {
        game = new GameEngine('X', new char[]{'X', 'X', 'O', 'O', 'O', 'X', 'X', 'X', 'O'});
        assertEquals(GameState.DRAW, game.checkVictory('X'));
    }

    @Test
    public void checkVictory_NearlyFullBoardReturnsGameInProgress() {
        game = new GameEngine('O', new char[]{'X', 'X', 'O', 'O', 'O', 'X', 'X', ' ', 'O'});
        assertEquals(GameState.GAME_IN_PROGRESS, game.checkVictory('O'));
    }

}