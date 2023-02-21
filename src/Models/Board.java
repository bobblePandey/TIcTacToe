package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Cell>> mBoard;


    public Board(int dimension) {
        mBoard = new ArrayList<>();
        for(int i = 0; i < dimension; i++) {
            List<Cell> row = new ArrayList<>();
            for(int j = 0; j < dimension; j++) {
                row.add(new Cell(i,j));
            }
            mBoard.add(row);
        }
    }

    public void display() {
        for (int i = 0; i < mBoard.size(); ++i) {
            for (int j = 0; j < mBoard.size(); ++j) {
                if (mBoard.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    System.out.printf("|   |");
                } else {
                    System.out.printf("| " + mBoard.get(i).get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println("\n");
        }
    }

    public List<List<Cell>> getBoard() {
        return mBoard;
    }

}
