package Model;

public class Cell {

    private boolean isMine;
    private boolean mineisRevealed;
    private boolean cellisRevealed;

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


    public Cell(boolean isMine, boolean mineisRevealed, boolean cellisRevealed, int mineCount) {
        this.isMine = isMine;
        this.mineisRevealed = mineisRevealed;
        this.cellisRevealed = cellisRevealed;
        this.mineCount = mineCount;
    }

    public void updateCount() {
         mineCount ++ ;
    }
    public void decreaseCount(){mineCount -- ; }

    public boolean getIsMine() {
        return isMine;
    }
    public void setMine() { isMine = true; }
    public boolean getmineIsRevealed() {
        return mineisRevealed;
    }
    public boolean getcellIsRevealed() { return cellisRevealed; }
    public void setmineIsRevealed(boolean revealed) { mineisRevealed = revealed; }
    public void setCellisRevealed(boolean revealed) {
        cellisRevealed = revealed;
    }
    public int getCount(){
        return mineCount;
    }
}
