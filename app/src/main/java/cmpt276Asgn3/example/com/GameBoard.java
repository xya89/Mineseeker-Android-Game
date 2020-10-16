package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import Model.Cell;

public class GameBoard extends AppCompatActivity {


    // TODO: select size of game board
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 7;

    private Cell cell = Cell.getInstance();

    // TODO: use rand to assign mines: determine if the cell is mine;
    // TODO: upon completion of the previous steps, delete the following codes;

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];


    // Temporary test variables
    private void setMineCells() {
        cell.setMine();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateButtons();

    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.TBL_gamepad);

        for (int row = 0;  row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);

            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++){

                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                // delete this
                button.setText("" + col + "," + row);

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
        lockButtonSizes();

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

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row ++ ) {
            for (int col = 0; col < NUM_COLS; col ++) {
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