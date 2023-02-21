package Controller;

import Models.Game;
import Models.GameState;
import Models.Player;
import strategy.GameWinningStrategy.GameWinningStrategy;

import java.util.List;

public class GameController {

   public Game createGame(int dimension, List<Player> players, GameWinningStrategy strategy) {

         try
         {
            Game newGame =  Game.getBuilder().setDimension(dimension).setPlayers(players).setGameWinningStrategy(strategy).build();
            return newGame;
         }
         catch (Exception e) {
            e.printStackTrace();
         }
         return null;
   }

   public void undoMove(Game game) {

   }

   public void display(Game game) {
       game.displayBoard();
   }

   public GameState getGameStatus(Game game) {
      return game.getCurrentState();
   }

   public Player getCurrentPlayer(Game game) {
      return game.getCurrentPlayer();
   }

   public void executeNextMove(Game game) {
      game.makeNextMove();
   }

   public Player getWinner(Game game) {
       return game.getWinner();
   }

}
