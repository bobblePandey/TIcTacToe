package strategy.GameWinningStrategy;

import Models.Board;
import Models.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleWinningStrategy implements GameWinningStrategy{

    List<HashMap<Character, Integer>> rowCountMap;
    List<HashMap<Character, Integer>> colCountMap;
    HashMap<Character, Integer> leftDiagonalCountMap;
    HashMap<Character, Integer> rightDiagonalCountMap;

    public SimpleWinningStrategy(int dimension) {

        rowCountMap = new ArrayList<>();
        colCountMap = new ArrayList<>();
        for(int i = 0; i < dimension; i++) {
            rowCountMap.add( new HashMap<>());
            colCountMap.add( new HashMap<>());
        }
        leftDiagonalCountMap = new HashMap<>();
        rightDiagonalCountMap = new HashMap<>();
    }

    @Override
    public boolean checkWinner(Board b, Cell c) {

        Character currSym = c.getPlayer().getSymbol();

        int row = c.getRow();
        int col = c.getCol();

        int currCount = rowCountMap.get(row).getOrDefault(currSym, 0);

        //set symbol count in row wise map
        rowCountMap.get(row).put(currSym, currCount + 1);

        currCount = colCountMap.get(col).getOrDefault(currSym, 0);

        //set symbol count in col wise map
        colCountMap.get(col).put(currSym, currCount + 1);

        //check for player win after current move row wise and col wise
        if( rowCountMap.get(row).get(currSym) == b.getBoard().size() || colCountMap.get(col).get(currSym) == b.getBoard().size() ) {
            return true;
        }

        //check for player win left diagonal
        if(row == col) {
            leftDiagonalCountMap.put(currSym, leftDiagonalCountMap.getOrDefault(currSym, 0) + 1);
            if(leftDiagonalCountMap.get(currSym) == b.getBoard().size()) {
                return true;
            }
        }


        //0 1 2
        //3 4 5
        //6 7 8
        //check for player win right diagonal
        if(row + col == b.getBoard().size() - 1) {
            rightDiagonalCountMap.put(currSym, rightDiagonalCountMap.getOrDefault(currSym, 0) + 1);
            if(rightDiagonalCountMap.get(currSym) == b.getBoard().size()) {
                return true;
            }
        }

        return false;
    }
}
