package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.controls.actions.BooleanAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/* Main Activity has a greeting animation, then automatically switch to menu after the animation.
*  Or with a skip button, to skip the animation and go directly to the main menu.*/

public class MainActivity extends AppCompatActivity {

    private boolean skipButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageAnimation(R.id.img_MainActivity, 4000);

        registerClickSkip();

    }

    private void imageAnimation(int image, int delayTime) {

        ImageView openingImage = findViewById(image);
        YoYo.with(Techniques.Wobble)
                .duration(700)
                .repeat(100)
                .playOn(openingImage);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                if (!skipButtonClicked){
                    Intent intent = MainMenu.makeIntent(MainActivity.this);
                    startActivity(intent);
                }
            }
        }, delayTime);

    }

    private void registerClickSkip() {
        Button btn= findViewById(R.id.btn_SkipAnimation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipButtonClicked = true;
                Intent intent = MainMenu.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

}