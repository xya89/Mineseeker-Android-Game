package cmpt276Asgn3.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hyperLinks();

    }

    private void hyperLinks() {
        // HyperLink to course home page
        TextView coursePageHyperLink = findViewById(R.id.txt_aboutTheAuthors);
        coursePageHyperLink.setMovementMethod(LinkMovementMethod.getInstance());

        // Link to Resources used in this game
        TextView landMinePNG = findViewById(R.id.txt_citations);
        landMinePNG.setMovementMethod(LinkMovementMethod.getInstance());

    }


    public static Intent makeIntent(Context context) {
        return new Intent(context, HelpActivity.class);
    }


}