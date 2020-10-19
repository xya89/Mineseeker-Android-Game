package cmpt276Asgn3.example.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

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
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Log messages for testing
                Log.i("TAG", "You clicked th dialog button");
            }
        };


        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Changing the message")
                .setView(view)
                .setPositiveButton(android.R.string.ok, listener)
                .create();

    }
}
