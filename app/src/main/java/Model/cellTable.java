package Model;

import android.opengl.ETC1;
import android.util.Log;

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
                if( (positionCell % length == postionCellOthers % length) || (positionCell/length == postionCellOthers/length) ){
                    if(c0.getIsMine()){c.updateCount();}
                }
            }

        }
    }

    public void generateCell(){
        for(int i = 0; i<length*width;i++) {

            Cell c1 = new Cell(false,false,false,0);
            cells.add(c1);
        }
    }

    public void generateMine(int mineNum){
        Random rand = new Random();
        //generate a array which contains all the randomly chosen mines' position index.
        int[] randArray = new int[mineNum];
        for(int i = 0;i<mineNum;i++){
            int randN =  rand.nextInt(length*width);
            /* check whether generated randN exists in array and if does, while loop will generate
            a new randN until the new randN never presents. */
            while(searchArr(randArray,randN)){
                randN = rand.nextInt(length*width);
            }
            randArray[i] = randN;
            Log.d("rand is ", Integer.toString(randArray[i]));
            cells.get(randArray[i]).setMine();
        }

    }


    public void updateMineCount(Cell c0){
        int positionClick  = cells.indexOf(c0);
        for( Cell c1 : cells ){
            int positionCell = cells.indexOf(c1);
            if( (positionCell % length == positionClick % length) || (positionCell/width == positionClick/width) ){
                c1.decreaseCount();
            }
        }
    }


    @NonNull
    @Override
    public Iterator<Cell> iterator() {
        return null;
    }
    //determine whether an integer is in an array.
    public boolean searchArr(int[] arr, int k){
        boolean flag = false;
        for(int j:arr){
            if (k == j) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
