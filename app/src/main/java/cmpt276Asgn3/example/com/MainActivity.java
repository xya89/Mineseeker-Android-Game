package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        registerClickMenu();



    }

    private void registerClickMenu() {
        Button btn= findViewById(R.id.btn_Menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainMenu.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }


    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenu.class);
    }


}