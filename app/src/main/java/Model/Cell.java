package Model;

/*A single cell object,
* contains feature of a cell*/

public class Cell {

    private boolean isMine;
    private boolean mineIsRevealed;
    private boolean cellIsRevealed;

    private int mineCount;

    // Add singleton support to Cell
    private static Cell instance;
    private Cell() {

    }
    public static Cell getInstance() {
        if (instance == null) {
            instance = new Cell();
        }
        return instance;
    }


    public Cell(boolean isMine, boolean mineIsRevealed, boolean cellIsRevealed, int mineCount) {
        this.isMine = isMine;
        this.mineIsRevealed = mineIsRevealed;
        this.cellIsRevealed = cellIsRevealed;
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

    public void setMineIsRevealed(boolean revealed) { mineIsRevealed = revealed; }
    public void setCellIsRevealed(boolean revealed) {
        cellIsRevealed = revealed;
    }

    public boolean getMineIsRevealed() {
        return mineIsRevealed;
    }
    public boolean getCellIsRevealed() { return cellIsRevealed; }

    public int getCount(){
        return mineCount;
    }
}
