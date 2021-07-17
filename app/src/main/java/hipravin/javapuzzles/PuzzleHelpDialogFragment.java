package hipravin.javapuzzles;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class PuzzleHelpDialogFragment extends DialogFragment {
    private final int helpMessageId;

    public PuzzleHelpDialogFragment(int helpMessageId) {
        this.helpMessageId = helpMessageId;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Puzzle solution advice");
        builder.setMessage(helpMessageId);
        builder.setPositiveButton("Ok...", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just
                // want it dismissed when clicked
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}