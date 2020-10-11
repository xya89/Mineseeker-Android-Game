package Model;

import android.opengl.ETC1;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cellTable implements Iterable<Cell> {
    private List<Cell> cells = new ArrayList<>();
    private int length;
    private int width;

    public cellTable(List<Cell> cells, int length, int width) {
        this.cells = cells;
        this.length = length;
        this.width = width;
    }


    public void updateCount(Cell c0){
        int positionClick  = cells.indexOf(c0);
        for( Cell c1 : cells ){
            int positionCell = cells.indexOf(c1);
            if( (positionCell % length == positionClick % length) || (positionCell/width == positionClick/width) ){
                c1.updateCount();
            }
        }
    }


    @NonNull
    @Override
    public Iterator<Cell> iterator() {
        return null;
    }
}
