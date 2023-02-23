package Models;

import strategy.GameWinningStrategy.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<Player> mPlayers;

    List<Move> mMoves;

    Board mGameBoard;

    GameState mCurrentState;

    GameWinningStrategy gameStrategy;

    Player winner = null;

    int currPlayerIndex;

    private Game(GameBuilder gameBuilder) {

        this.mGameBoard = new Board(gameBuilder.dimension);
        this.mPlayers = gameBuilder.players;
        this.currPlayerIndex = 0;
        this.mCurrentState = GameState.IN_PROGRESS;
        this.mMoves = new ArrayList<>();
        this.gameStrategy = gameBuilder.strategy;

    }

    public void undoMove() {

        if(mMoves.size() < 1) {
            System.out.println("No moves done yet");
            return;
        }

        currPlayerIndex--;
        if(currPlayerIndex < 0 ) {
            currPlayerIndex += mPlayers.size();
        }

        Move lastMove = mMoves.get( mMoves.size() - 1 );

        mGameBoard.undoLastMove(lastMove.getCell());

        gameStrategy.undoLastMove(lastMove.getCell());

        mMoves.remove(lastMove);
    }

    public void makeNextMove() {

        //get player whose next chance is
        Player currentPlayer = mPlayers.get(currPlayerIndex);

        //get move from current player
        Move m = currentPlayer.getNextMove(mGameBoard);
        Cell movedCell = m.getCell();

        //make move, set move cell state to FILLED & cell Player to current player.
        mGameBoard.getBoard().get(movedCell.getRow()).get(movedCell.getCol()).setCellState(CellState.FILLED);
        mGameBoard.getBoard().get(movedCell.getRow()).get(movedCell.getCol()).setPlayer(currentPlayer);

        //add current move to move list for undo.
        mMoves.add(m);

        if( gameStrategy.checkWinner(movedCell) ) {
            winner = currentPlayer;
            mCurrentState = GameState.ENDED;
        }
        else if( mMoves.size() == mGameBoard.getBoard().size() * mGameBoard.getBoard().size() ) {
            mCurrentState = GameState.DRAW;
        }

        //increase index of current player within bounds
        currPlayerIndex++;
        currPlayerIndex %= mPlayers.size();

    }

    public GameState getCurrentState() {
        return mCurrentState;
    }

    public Player getCurrentPlayer() {
        return mPlayers.get(currPlayerIndex);
    }

    public void setCurrentState(GameState mCurrentState) {
        this.mCurrentState = mCurrentState;
    }

    public void displayBoard() {
        mGameBoard.display();
    }

    public Player getWinner() {
        return winner;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public static class GameBuilder {

        int dimension;
        List<Player> players;

        GameWinningStrategy strategy;

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setGameWinningStrategy(GameWinningStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public boolean validate() {
            if( this.dimension != players.size() + 1) {
                return false;
            }
            return true;
        }

        public Game build() throws Exception{
            if( !validate() ) {
                throw new Exception("PlayerDimensionMismatchException");
            }
            return new Game(this);
        }

    }


}
