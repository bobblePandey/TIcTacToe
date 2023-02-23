import Controller.GameController;
import Models.Game;
import Models.GameState;
import Models.HumanPlayer;
import Models.Player;
import strategy.GameWinningStrategy.SimpleWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Dimension on board");

        int dimension = sc.nextInt();

        int numOfPlayers = dimension - 1;

        List<Player> players = new ArrayList<>();

        for(int i = 0; i < numOfPlayers; i++) {
            System.out.println("Enter name of player : " + i);
            String name = sc.next();
            System.out.println("Enter symbol of player : " + i);
            char sym = sc.next().charAt(0);

            Player p = new HumanPlayer(name, sym);

            players.add(p);
        }

        GameController gc = new GameController();

        Game boardGame = gc.createGame(dimension, players, new SimpleWinningStrategy(dimension));

        while ( gc.getGameStatus(boardGame) == GameState.IN_PROGRESS ) {
            System.out.println("This is " + gc.getCurrentPlayer(boardGame).getName() + "'s chance");

            System.out.println("So you want to undo last move (y/n)");

            char undoMove = sc.next().charAt(0);

            if( undoMove == 'y' || undoMove == 'Y') {
                gc.undoMove(boardGame);
            }
            else {
                System.out.println("Enter your move");
                gc.executeNextMove(boardGame);
            }

            gc.display(boardGame);
        }

        System.out.println("Game has ended. Result was: ");
        if (!boardGame.getCurrentState().equals(GameState.DRAW)) {
            System.out.println("Winner is: " + gc.getWinner(boardGame).getName());
        }
        else {
            System.out.println("Game Draw :( \n Better luck next time");
        }
    }
}