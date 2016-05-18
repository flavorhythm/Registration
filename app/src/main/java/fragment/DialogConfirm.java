package fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by zyuki on 5/17/2016.
 */
public class DialogConfirm extends DialogFragment implements AlertDialog.OnClickListener {
    private AlertDialog.Builder dialog;

    private Callback callback;

    public static DialogConfirm newInstance() {
        return new DialogConfirm();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (Callback)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = new AlertDialog.Builder(getContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog.setTitle("Is this your unit?")
                .setMessage("TC-210")
                .setPositiveButton("pos", this)
                .setNegativeButton("neg", this)
                .create();

        return dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(which) {
            case AlertDialog.BUTTON_POSITIVE:
                callback.showSnackbar(true);

                getDialog().dismiss();
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                getDialog().dismiss();
                break;
        }
    }

    private AlertDialog.Builder buildConfirm() {
        return new AlertDialog.Builder(getContext())
                .setTitle("confirm")
                .setMessage("confirmed")
                .setPositiveButton("pos", this)
                .setNegativeButton("neg", this);
    }

    public interface Callback {
        void showSnackbar(boolean success);
    }
}
