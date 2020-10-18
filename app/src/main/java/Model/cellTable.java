package Model;

import android.opengl.ETC1;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class cellTable implements Iterable<Cell> {
    private List<Cell> cells = new ArrayList<>();
    private int length;
    private int width;

    public cellTable( int length, int width,List<Cell> cells) {
        this.cells = cells;
        this.length = length;
        this.width = width;
    }

    public List<Cell> getlist(){
        return cells;
    }

    public void InitiateMineCount(){
        for(Cell c:cells){
            int positionCell = cells.indexOf(c);
            for( Cell c0:cells){
                int postionCellOthers = cells.indexOf(c0);
                if( (positionCell % length == postionCellOthers % length) || (positionCell/width == postionCellOthers/width) ){
                    if(c0.getIsMine()){c.updateCount();}
                }
            }

        }
    }

    public void generateCell(){
        for(int i = 0; i<length*width;i++) {

            Cell c1 = new Cell(false,false,0);
            cells.add(c1);
        }
    }
    public void generateMine(int mineNum){
        Random rand = new Random();
        int[] randArray = new int[mineNum];
        for(int i = 0;i<mineNum;i++){
            randArray[i] = rand.nextInt(length*width);
            cells.get(randArray[i]).setMine();
        }
       /* for( int j = 0; j< length*width; j++ ){
            if (Arrays.asList(randArray).contains(j)) {
                cells.get(j).setMine();
            }

        }*/
    }


    public void updateMineCount(Cell c0){
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
