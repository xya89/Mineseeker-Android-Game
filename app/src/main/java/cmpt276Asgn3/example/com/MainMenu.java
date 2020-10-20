package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/* Game Menu, user can navigate to Play: game play; Options: configure game; Help: access information
* about the game. */

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        registerClickPlay();

        registerClickOptions();

        registerClickHelp();


    }

    private void registerClickOptions() {
        Button btn = findViewById(R.id.btn_Options);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionActivity.makeIntent(MainMenu.this);
                startActivity(intent);
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
    private void registerClickHelp() {
        Button btn = findViewById(R.id.btn_Help);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HelpActivity.makeIntent(MainMenu.this);
                startActivity(intent);
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenu.class);
    }

}