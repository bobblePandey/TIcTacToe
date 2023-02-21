package Models;

public class Cell {

    private int row, col;
    private Player player;
    private CellState mState;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.mState = CellState.EMPTY;
        this.player = null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellState() {
        return mState;
    }

    public void setCellState(CellState cellState) {
        this.mState = cellState;
    }


}
