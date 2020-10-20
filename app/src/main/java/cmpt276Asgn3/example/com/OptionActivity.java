package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Model.Cell;
/* Optional Activity:
* User can configure number of targets
* User can configure the size of game board
* Values of user configuration are saved in SharedPreference via save... functions*/
public class OptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String NUM_CELLS_IN_ROW = "Num cells in row";
    public static final String NUM_CELLS_IN_COLUMN = "Num cells in Column";
    public static final String PREFS = "AppPrefs";
    public static final String NUM_SELECTED_MINES = "Num selected mines";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        boardSizeSpinner();
        numberOfMineSpinner();

    }


    private void numberOfMineSpinner() {
        // Number of mines options - spinner
        //*The following code is quoted from https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner numMinesSpinner = findViewById(R.id.spinner_numMines);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_of_mines_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        numMinesSpinner.setAdapter(adapter);
        numMinesSpinner.setOnItemSelectedListener(this);

    }
    private void boardSizeSpinner(){
        // Board size options - spinner
        //*The following code is quoted from https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner boardSizeSpinner = findViewById(R.id.spinner_boardSize);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.board_size_options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        boardSizeSpinner.setAdapter(adapter);

        boardSizeSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        ((TextView)spinner.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView)spinner.getChildAt(0)).setTextSize(16);

        if (spinner.getId() == R.id.spinner_boardSize)
        {
            String text = parent.getItemAtPosition(position).toString();
            saveGameBoardRow(Integer.parseInt(text.replaceAll(" x.*", "")));
            saveGameBoardColumn(Integer.parseInt(text.replaceAll(".*x ", "")));
        }
        else if (spinner.getId() == R.id.spinner_numMines)
        {
            String text = parent.getItemAtPosition(position).toString();
            saveNumMineSet(Integer.parseInt(text));

        }
    }

    private void saveGameBoardRow(int numRows) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(NUM_CELLS_IN_ROW, numRows);
        editor.apply();
    }
    static public int getGameBoardRow(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, MODE_PRIVATE);
        return prefs.getInt(NUM_CELLS_IN_ROW, 2);
    }

    private void saveGameBoardColumn(int numCol) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(NUM_CELLS_IN_COLUMN, numCol);
        editor.apply();
    }
    static public int getGameBoardColumn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, MODE_PRIVATE);
        return prefs.getInt(NUM_CELLS_IN_COLUMN, 2);
    }

    private void saveNumMineSet(int numMines) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(NUM_SELECTED_MINES, numMines);
        editor.apply();
    }
    static public int getNumMineSet(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, MODE_PRIVATE);
        // TODO: Change default value.
        return prefs.getInt(NUM_SELECTED_MINES, 0);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionActivity.class);
    }
}