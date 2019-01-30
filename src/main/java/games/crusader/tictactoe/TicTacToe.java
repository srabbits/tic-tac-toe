package games.crusader.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        GameEngine game = new GameEngine();
        while (game.getState() == GameState.GAME_IN_PROGRESS ) {
            System.out.println(game.printBoard());
            try {
                System.out.print("Player " + game.getPlayer() + " move[0-8]:  ");
                int slot = scan.nextInt();
                game.select(slot);
            } catch (TicTacToeException e) {
                System.out.println(ANSI_RED + "Error:" + e.getMessage() + ANSI_RESET);
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Error:" + "No letters DUMBASS" + ANSI_RESET);
                scan.nextLine();


            }


        }
        System.out.println(game.printBoard());
        switch (game.getState()) {

            case X_WINS:
                System.out.println("X: WINS!");
                break;
            case O_WINS:
                System.out.println("O: WINS!");
                break;
            case DRAW:
                System.out.println("You both lose!");
                break;
        }
    }

}
