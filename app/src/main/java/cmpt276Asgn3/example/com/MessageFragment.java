package cmpt276Asgn3.example.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MessageFragment extends AppCompatDialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create the view to show
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.game_end_message_layout, null);



        // Create a button Listener
        Button btn = view.findViewById(R.id.btn_mesaageFragmentOK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainMenu.class);
                startActivity(intent);
            }
        });


        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

    }

}
