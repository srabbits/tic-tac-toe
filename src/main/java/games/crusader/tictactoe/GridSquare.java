package games.crusader.tictactoe;

public enum GridSquare {
    EMPTY(' '), X('X'), O('O');

    private final char ch;

    GridSquare(char ch) {
        this.ch = ch;
    }

    @Override
    public String toString(){
        return "" + ch;
    }

}
