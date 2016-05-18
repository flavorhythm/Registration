package fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

/**
 * Created by zyuki on 5/16/2016.
 */
public class DialogAddUnit extends DialogFragment implements AlertDialog.OnClickListener {
    private AlertDialog.Builder inputDialog;
    private EditText serialNum;

    public static DialogAddUnit newInstance() {
        return new DialogAddUnit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serialNum = new EditText(getContext());
        inputDialog = buildInput();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return inputDialog.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(which){
            case AlertDialog.BUTTON_POSITIVE:
                DialogRouter.showConfDialog(getActivity());
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                getDialog().dismiss();
                break;
        }
    }

    private AlertDialog.Builder buildInput() {
        return new AlertDialog.Builder(getContext())
                .setTitle("input")
                .setView(serialNum)
                .setPositiveButton("pos", this)
                .setNegativeButton("neg", this);
    }
}
