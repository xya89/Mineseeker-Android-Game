package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import Model.Cell;

public class GameBoard extends AppCompatActivity {


    // TODO: select size of game board - must work with cell table
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 10;

    private int testRow = 4;
    private int testCol = 6;

    private Cell cell = Cell.getInstance();

    // TODO: use rand to assign mines: determine if the cell is mine;

    Button buttons[][] = new Button[testRow][testCol];


    // Temporary test variables
    private void setMineCells() {
        cell.setMine();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int test = OptionActivity.getGameBoardRow(this);
        Toast.makeText(this, "saved optional row: " + test, Toast.LENGTH_SHORT).show();
        //testCol = OptionActivity.getGameBoardColumn(this);

        populateButtons(testRow,testCol);

    }

    private void populateButtons(int numRow, int numCol) {
        TableLayout table = findViewById(R.id.TBL_gamepad);

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

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);

                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        Toast.makeText(this, "Button clicked: " + col + "," + row, Toast.LENGTH_SHORT).show();

        Button button = buttons[row][col];

        // Lock Button Sizes;
//        testRow = OptionActivity.getGameBoardRow(this);
//        testCol = OptionActivity.getGameBoardColumn(this);

        lockButtonSizes(testRow, testCol);

        // temp functions, delete this
        setMineCells();

        if (cell.getIsMine()){
            // TODO: cell button becomes a image of mine
            showMineImage(button);

        }
        else if (!cell.getIsMine()){
            // TODO: cell button display number of mines left in the same row and same col.
        }
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

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameBoard.class);
    }
}