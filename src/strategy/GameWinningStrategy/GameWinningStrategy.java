package strategy.GameWinningStrategy;

import Models.Board;
import Models.Cell;

public interface GameWinningStrategy {

    boolean checkWinner(Board b, Cell c);

}
