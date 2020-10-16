package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // populate spinners options
        boardSizeSpinner();
        numberOfMineSpinner();




    }

    // populate spinners
    //TODO: Actually funcationalize the spinners
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
        // Test code below, rewrite to functionalize this
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinner_boardSize)
        {
            Toast.makeText(parent.getContext(), "spinner 1", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.spinner_numMines)
        {
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionActivity.class);
    }
}