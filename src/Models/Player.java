package Models;

public abstract class Player {
    public char mSymbol;
    public String mName;


    public abstract Move getNextMove(Board b);

    public char getSymbol() {
        return mSymbol;
    }

    public String getName() {
        return mName;
    }
}
