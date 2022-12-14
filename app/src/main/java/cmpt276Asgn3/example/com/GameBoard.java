package cmpt276Asgn3.example.com;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Cell;
import Model.cellTable;

/*GameBoard Activity:
* UI code for the main game*/

public class GameBoard extends AppCompatActivity {

    private int timeScan = 0;
    private int foundMines = 0;

    Button[][] buttons;


    private cellTable setCells(int numCol, int numRow) {
        cellTable tableList = new cellTable(numCol, numRow, new ArrayList<Cell>());
        tableList.generateCell();
        tableList.generateMine(OptionActivity.getNumMineSet(this));
        tableList.InitiateMineCount();
        return tableList;
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttons = new Button[OptionActivity.getGameBoardRow(this)][OptionActivity.getGameBoardColumn(this)];

        // set messages for "Found %d of %d mines."
        TextView tvTotalMines = findViewById(R.id.txt_numMines);
        tvTotalMines.setText(Integer.toString(OptionActivity.getNumMineSet(this)));

        populateButtons(OptionActivity.getGameBoardRow(this),OptionActivity.getGameBoardColumn(this));
    }

    private void populateButtons(final int numRow, final int numCol) {
        TableLayout table = findViewById(R.id.TBL_gamepad);

        final cellTable cellTableToUse = setCells(numCol,numRow);

        for (int row = 0;  row < numRow; row++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < numCol; col++){

                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                final Button button = new Button(this);

                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));


                // Make text not clip on small buttons
                button.setPadding(0,0,0,0);

                // Set button background
                // There is actually a bug inside but i hope you won't encounter it.
                showButtonImage(button, 270, 270, R.drawable.stash_small);

                Log.d("The size before is:" , Integer.toString(button.getWidth()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL,FINAL_ROW, FINAL_ROW*numCol+FINAL_COL,cellTableToUse);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;

            }
        }
    }


    @SuppressLint("SetTextI18n")
    private void gridButtonClicked(int col, int row, int index, cellTable cellTable) {

        Button button = buttons[row][col];

        Log.d("The size is:" , Integer.toString(button.getWidth()));

        if(cellTable.getlist().get(index).getIsMine()){
            if(!cellTable.getlist().get(index).getMineIsRevealed() ){

                showButtonImage(button, button.getWidth(), button.getHeight(), R.drawable.explosive_timed);

                cellTable.getlist().get(index).setMineIsRevealed(true);
                foundMines++;
                updateFoundMines(foundMines);

                cellTable.updateMineCount(cellTable.getlist().get(index));

                for(Cell cell2 : cellTable.getlist()){
                    int boardCol = OptionActivity.getGameBoardColumn(this);
                    int positionCell = cellTable.getlist().indexOf(cell2);
                    int tempRow = positionCell % boardCol;
                    int tempCol = positionCell / boardCol;
                    Log.d("temp Row is ",Integer.toString(tempRow));
                    Log.d("temp Col is ",Integer.toString(tempCol));
                    if(cell2.getCellIsRevealed() && (tempRow == index%boardCol || tempCol == index/boardCol)){

                        buttons[tempCol][tempRow].setText(Integer.toString(cell2.getCount()));
                    }
                }
            }
        }

        else{
            if(!cellTable.getlist().get(index).getCellIsRevealed()) {
                cellTable.getlist().get(index).setCellIsRevealed(true);
                button.setText(Integer.toString(cellTable.getlist().get(index).getCount()));
                button.setTextColor(Color.WHITE);

                timeScan++;
                updateScansUsed(timeScan);
            }
        }

        lockButtonSizes(OptionActivity.getGameBoardRow(this), OptionActivity.getGameBoardColumn(this));

        if(foundMines == OptionActivity.getNumMineSet(this)){

            gameFinishMessage();
        }

    }

    @SuppressLint("SetTextI18n")
    private void updateFoundMines(int numFoundMines) {
        TextView tvMineFound = findViewById(R.id.txt_foundMinesCount);
        tvMineFound.setText(Integer.toString(numFoundMines));

    }
    @SuppressLint("SetTextI18n")
    private void updateScansUsed(int scan) {
        TextView tvScan = findViewById(R.id.txt_scanCount);
        tvScan.setText(Integer.toString(scan));
    }

    private void showButtonImage(Button button, int newWidth, int newHeight ,int sourceDrawable) {
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), sourceDrawable);
        Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaleBitmap));
    }
    private void lockButtonSizes(int numRow, int numCol) {
        for (int row = 0; row < numRow; row ++ ) {
            for (int col = 0; col < numCol; col ++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void gameFinishMessage() {
        FragmentManager manager = getSupportFragmentManager();
        MessageFragment dialog = new MessageFragment();
        dialog.show(manager, "MessageDialog");

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameBoard.class);
    }
}