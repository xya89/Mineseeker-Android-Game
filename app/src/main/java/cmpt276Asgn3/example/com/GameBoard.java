package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Cell;
import Model.cellTable;

public class GameBoard extends AppCompatActivity {

    private int timeScan = 0;
    private int foundMines = 0;

    Button buttons[][];


    //create cellTable separately, which generates cells and mines.
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

    private void populateButtons(int numRow, final int numCol) {
        TableLayout table = findViewById(R.id.TBL_gamepad);

        final cellTable t2 = setCells(numCol,numRow);


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

                button.setPadding(0,0,0,0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL,FINAL_ROW, FINAL_ROW*numCol+FINAL_COL,t2);

                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void gridButtonClicked(int col, int row, int index, cellTable c1) {

        Button button = buttons[row][col];

        //cell button becomes an image of mine when clicking mine button.
        if(c1.getlist().get(index).getIsMine()){
            if(!c1.getlist().get(index).getIsRevealed()) {

                showMineImage(button);
                c1.getlist().get(index).setRevealed(true);
                foundMines++;
                updateFoundMines(foundMines);
            }
        }
        else{
            if(!c1.getlist().get(index).getIsRevealed()) {
                c1.getlist().get(index).setRevealed(true);
                button.setText(Integer.toString(c1.getlist().get(index).getCount()));

                timeScan++;
                updateScansUsed(timeScan);
            }
        }

        lockButtonSizes(OptionActivity.getGameBoardRow(this), OptionActivity.getGameBoardColumn(this));
        if(foundMines ==OptionActivity.getNumMineSet(this)){

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


    private void showMineImage(Button button) {
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rust_landmine_png);
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