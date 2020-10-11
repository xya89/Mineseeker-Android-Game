package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        registerClickPlay();

        registerClickOptions();


    }

    private void registerClickOptions() {
        // TODO: select the board size

        Button btn = findViewById(R.id.btn_Options);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: select the borad size, three options

            }
        });

    }

    private void registerClickPlay() {
        Button btn = findViewById(R.id.btn_ToGame);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameBoard.makeIntent(MainMenu.this);
                startActivity(intent);

            }
        });
    }


    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, MainMenu.class);
        return intent;
    }

}