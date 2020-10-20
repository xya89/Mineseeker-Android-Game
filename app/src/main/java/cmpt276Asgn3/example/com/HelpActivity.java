package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
/*  Help Activity, display game information and provide external access to sources used in this game*/
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hyperLinks();

    }

    private void hyperLinks() {

        TextView coursePageHyperLink = findViewById(R.id.txt_aboutTheAuthors);
        coursePageHyperLink.setMovementMethod(LinkMovementMethod.getInstance());


        TextView landMinePNG = findViewById(R.id.txt_citations);
        landMinePNG.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, HelpActivity.class);
    }


}