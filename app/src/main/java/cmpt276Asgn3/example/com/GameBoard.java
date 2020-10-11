package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class GameBoard extends AppCompatActivity {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        popoulateButtons();

    }

    private void popoulateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.TBL_gamepad);

        for (int row = 0;  row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);

            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++){
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked();
                    }
                });

                tableRow.addView(button);
            }
        }
    }

    private void gridButtonClicked() {
        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show();
    }

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, GameBoard.class);
        return intent;
    }
}