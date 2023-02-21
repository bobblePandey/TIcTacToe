package Models;

import Dtos.NextMoveInput;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, char symbol) {
        this.mName = name;
        this.mSymbol = symbol;
    }

    @Override
    public Move getNextMove(Board b) {

        Move m = NextMoveInput.getNextHumanPlayerInput();
        m.getCell().setPlayer(this);
        m.getCell().setCellState(CellState.FILLED);

        return m;
    }
}
