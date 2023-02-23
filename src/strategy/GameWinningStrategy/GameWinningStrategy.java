package strategy.GameWinningStrategy;

import Models.Board;
import Models.Cell;

public interface GameWinningStrategy {

    boolean checkWinner(Cell c);

    void undoLastMove(Cell cell);

}
