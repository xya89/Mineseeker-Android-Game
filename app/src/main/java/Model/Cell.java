package Model;

public class Cell {

    private boolean isMine;
    private boolean isRevealed;

    // if the cell is not mine, then cell contains a number
    private int mineCount;


    // Add singleton support to Cell
    private static Cell instance;
    private Cell() {
        // private to prevent anyone else from instantiating
    }
    public static Cell getInstance() {
        if (instance == null) {
            instance = new Cell();
        }
        return instance;
    }

    public void updateCount() {
         mineCount ++ ;
    }

    public boolean getIsMine() {
        return isMine;
    }
    public void setMine(boolean mine) {
        isMine = mine;
    }
    public boolean getIsRevealed() {
        return isRevealed;
    }
    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }
}
